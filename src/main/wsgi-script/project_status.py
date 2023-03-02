#!/bin/python

import psycopg2,json

def show_status():
  conn = psycopg2.connect("dbname=cmdbuild host=10.0.250.240 user=postgres password=Itis@2015")
  cur = conn.cursor()
  cur.execute("""select c."Code",
                        c.customer_id,
                        ps."Code", 
                        TRIM(ps."Status_TXT"), 
                        (select "Code" from "LookUp" where "Id" = ps."Status_Code"), 
                        (select "Code" from "LookUp" where "Id" = ps."Type_Of_Support"),
                        TO_CHAR(ps."DateOfSDWANInstallation", 'DD-MON-YYYY') as dateofsdwaninstallation,
                        TO_CHAR(ps."Kickoff_date", 'DD-MON-YYYY') as kickoff_date,
                        ps."POC",
                        ps."WAEs",
                        ps."CountOfLocations",
                        ps."OnPremise", 
                        ps."documentation_link"
                     from 
                     sdwan_project_status ps 
                     join "Map_projects_customer" pc 
                        on ps."Status" = 'A' and pc."Status" = 'A' and ps."Id" = pc."IdObj1"
                     join customers c
                        on c."Status" = 'A' and c."Id" = pc."IdObj2"
 """)
  res = cur.fetchall()
  cur.close()
  conn.close()

  arr = []
  for line in res:
    (cus_name, cus_id, nav_nr, status_txt, status_code, typeOfSupport, DateOfSDWAN, DateOfKickoff, POC, WAEs, CountOfLocations, OnPremise, DocuLink) = line
    elem = {
      "customer_name" : cus_name,
      "customer_id"   : cus_id,
      "navision_nr"   : nav_nr,
      "StatusText"    : status_txt, 
      "StatusCode"    : status_code, 
      "TypeOfSupport" : typeOfSupport, 
      "DateOfSDWANInstallation"   : DateOfSDWAN, 
      "DateOfKickoff" : DateOfKickoff, 
      "IsPOC"         : POC, 
      "IsWAE"         : WAEs, 
      "CountOfLocations" : CountOfLocations, 
      "IsOnPremise"   : OnPremise, 
      "Link2Documentation" : DocuLink
    }
    arr.append(elem)
    
  return json.dumps(arr)


def application(environ, start_response):
    status = '200 OK'
    output = "OK"

    RequestMethod = environ.get('REQUEST_METHOD')
    QueryString = environ.get('QUERY_STRING')

    if RequestMethod == "GET":
        output = show_status()
    else:
        status = '404 Error'
        output = "No valid http method"

    response_headers = [('Content-type', 'text/json'),
                        ('Content-Length', str(len(output)))]
    start_response(status, response_headers)
 
    return [output]

