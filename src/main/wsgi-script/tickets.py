import os, pprint
import urlparse
import json, ast
import urllib2
import datetime
from dateutil import tz

FileDest = '/tmp/tickets.json'

pp = pprint.PrettyPrinter(indent=2)

def show_ticket():
    output = ""
    errorString = ""

    tickets = {}

    try:
        resp = urllib2.urlopen( 'http://10.0.250.100:12000/api/v1/zammad/GetOpenSDWanTickets' )
        data = resp.read()

        #print data
        data1 = json.loads( data )["hits"]
        tickets = data1["hits"]

    except urllib2.HTTPError as e:
        errorString = ('The server couldn\'t fulfill the request - Error code: ', e.code)
    except urllib2.URLError as e:
        errorString = ('We failed to reach a server - Reason: ', e.reason)

    # Muss noch umgesetzt werden
    # https://stackoverflow.com/questions/18662967/convertto-json-an-array-with-a-single-item  

    # pp.pprint(tickets)

    #output += "<link rel='stylesheet' type='text/css' href='/table.data.css'>"
    output += "<div class='nav_tickets'>"
    #output += "<script type='text/JavaScript'>function TimedRefresh( t ) { setTimeout('location.reload(true);', t); }</script>"
    output += """<style>
.tooltip {
    position: relative;
    display: inline-block;
    border-bottom: 1px dotted black;
}

.tooltip .tooltiptext {
    visibility: hidden;
    width: 500px;
    background-color: #555;
    color: #fff;
    text-align: center;
    border-radius: 6px;
    padding: 5px 0;
    position: absolute;
    z-index: 1;
    top: 0%;
    left: 0%;
    margin-left: 0px;
    opacity: 0;
    transition: opacity 1s;
}

.tooltip .tooltiptext::after {
    content: "";
    position: absolute;
    top: 100%;
    left: 50%;
    margin-left: -5px;
    border-width: 5px;
    border-style: solid;
    border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
    visibility: visible;
    opacity: 1;
}
</style>"""

    #output += "<body onload='JavaScript:TimedRefresh(30000);'>"
    output += "<table class='data table' style='font-size:9pt;font-family: arial, helvetica, sans-serif;'>"
    output += "<tr class='data'>"   
    output += "<th>%s</th>" %  'Site'
    output += "<th>%s</th>" %  'Ticket'
    #output += "<th>%s</th>" %  'Location'
    output += "<th>%s</th>" %  'Comment'
    output += "<th>%s</th>" %  'Professional'
    output += "<th>%s</th>" %  'Created at'
    output += "</tr>"
    
    if len(tickets) == 0:
        output += "<tr class='data'>"
        output += "<td>%s</td>" %  "-"
        output += "<td>%s</td>" %  "-"
        #output += "<td>%s</td>" %  "-"
        output += "<td>%s</td>" %  "Perfect" if (errorString == "") else errorString
        output += "<td>%s</td>" %  "-"
        output += "<td>%s</td>" %  "-"
        output += "</tr>"

    for ticket in tickets:
        SiteName = ticket['_source']['organization'].encode('utf8', 'ignore')
        #Location = ticket['Your Reference'].encode('ascii', 'ignore')
        Comment = ticket['_source']['title'].encode('utf8', 'ignore').replace('\n','<br>')
        No = ticket['_source'].get( 'number', ticket['_id'] ).encode('utf8', 'ignore')
        #No = ticket['_id'].encode('utf8', 'ignore')

	#Date = datetime.datetime.strptime( ticket['_source']['created_at'].encode("utf8", "ignore")[:19], "%Y-%m-%dT%H:%M:%S" )
        DateUtc = datetime.datetime.strptime( ticket['_source']['created_at'].encode("utf8", "ignore")[:19], "%Y-%m-%dT%H:%M:%S" )
	DateUtc = DateUtc.replace( tzinfo=tz.tzutc() )
	Date = DateUtc.astimezone( tz.tzlocal() )

        output += "<tr class='data'>"   
        output += "<td>%s</td>" %  SiteName

        output += "<td style='text-align:center;'><a href='https://support.ta-systeme.com/#ticket/zoom/%s' target='_blank' style='color:#0000FF; text-decoration:underline;'</a>%s</td>" %  (ticket['_id'].encode('utf8', 'ignore'), No)
        #output += "<td style='text-align:center;'><a href='https://support.ta-systeme.com/#ticket/zoom/%s' target='_blank' style='color:#0000FF; text-decoration:underline;'</a>%s</td>" %  (No, No)

        #output += "<td><a href='/velocloud/check_mk/view.py?host=%s&site=%s_oob&view_name=host' target='_top'>%s</a></td>" %  (Location, SiteName, Location)

        output += "<td><div class='tooltip'>%s<span class='tooltiptext'>%s</span></div></td>" %  (Comment.replace('<br>',' ')[0:160], Comment)

        #output += "<td>%s</td>" %  ticket['_source']['owner']['login'].encode('utf8', 'ignore')
        output += "<td>%s&nbsp;%s</td>" %  (ticket['_source']['owner']['firstname'].encode('utf8', 'ignore'), ticket['_source']['owner'].get("lastname", "").encode('utf8', 'ignore'))

        #output += "<td>%s</td>" %  Date.ticket['_source']['created_at'].encode('utf8', 'ignore')
        output += "<td style='text-align:center;'>%s</td>" %  Date.strftime("%d.%m.%Y %H:%M")

        output += "</tr>"

    output += "</table>"
    output += "</div>"

    return output


def write_ticket(environ):

    body= ''  # b'' for consistency on Python 3.0
    try:
        length = int(environ.get('CONTENT_LENGTH', '0'))
    except ValueError:
        length = 0
    if length != 0 :
        body = environ['wsgi.input'].read(length)
        parsed_body = urlparse.parse_qs(body)

        try:
            ticket = ''.join(parsed_body["ticket"])
        except KeyError:
            if os.path.isfile(FileDest):
                os.remove(FileDest)
            return None
        f = open('/tmp/tickets.tmp.json','w+')
        f.write(ticket)
        f.close()
        try:
            os.remove(FileDest)
        except OSError:
            pass
        os.rename('/tmp/tickets.tmp.json',FileDest)
    return None

 
def application(environ, start_response):
    status = '200 OK'
    output = "OK"

    RequestMethod = environ.get('REQUEST_METHOD')
    QueryString = environ.get('QUERY_STRING')

    if RequestMethod == "POST":
        write_ticket(environ)
    elif RequestMethod == "GET":
        output = show_ticket()
    else:
        status = '404 Error'
        output = "No valid http method"

    response_headers = [('Content-type', 'text/html'),
                        ('Content-Length', str(len(output)))]
    start_response(status, response_headers)
 
    return [output]

