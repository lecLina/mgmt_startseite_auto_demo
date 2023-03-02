<?php

require 'cache.php';
require 'tools.php';

function getWorkingAreaContent($URL, $APITOKEN, $WORKID, $LOCID)
{
  date_default_timezone_set("Europe/Berlin");

  $today = date("c");
  $end = date("c", strtotime("+2 week"));

  $decoded = json_decode(getUrl("$URL/shifts?$APITOKEN&location_id=$LOCID&working_area_id=$WORKID&range_start=$today&range_end=$end"));

  $result = array();

  foreach ($decoded->{'shifts'} as $shift)
  {
    $obj = new usershift();
    $obj->startdate = $shift->{'starts_at'};
    $obj->shiftid = $shift->{'id'};
    $result[] = $obj;
  }
  // Array should be sorted based on startdate
  uasort($result, 'cmpDate');

  foreach($result as $elem)
  {
    $decoded = json_decode(getUrl("$URL/assignments?$APITOKEN&shift_id=$elem->shiftid"));
    // PHP Warning:  Undefined array key 0 in /var/www/html/papershift.php on line
    // Solution add : null coalesce operator
    $elem->username = $decoded->{'users'}->{'assigned'}[0]->{'username'} ?? "";
  }

  return $result;
}

function getPapershift()
{
  $PARAM='-k -s -H "Content-Type: application/json" -H "Accept: application/json"';
  $URL="https://app.papershift.com/public_api/v1";
  $APITOKEN="api_token=x9YvfT1eGbRp0mIGcRDkx7iJmF8cbpFisP2fi6zJ";

  $decoded = json_decode(getUrl("$URL/working_areas?$APITOKEN"));

  foreach ($decoded->{'working_areas'} as $area)
  {
    if ($area->{'name'} == 'Rufbereitschaft')
    {
      $WORKID_RUF = $area->{'id'};
      $LOCID_RUF = $area->{'location_id'};
    } 
    else if ($area->{'name'} == 'Konfigurieren und Testen')
    {
      $WORKID_TEST = $area->{'id'};
      $LOCID_TEST = $area->{'location_id'};
    }
    else if ($area->{'name'} == 'Servicedesk')
    {
      $WORKID_DESK = $area->{'id'};
      $LOCID_DESK = $area->{'location_id'};
    }
  }

  $result_ruf = getWorkingAreaContent($URL, $APITOKEN, $WORKID_RUF, $LOCID_RUF);
  $result_test = getWorkingAreaContent($URL, $APITOKEN, $WORKID_TEST, $LOCID_TEST);
  $result_desk = getWorkingAreaContent($URL, $APITOKEN, $WORKID_DESK, $LOCID_DESK);
  
  return array(
    'Rufbereitschaft' => $result_ruf,
    'Testing' => $result_test,
    'Servicedesk' => $result_desk
  );  
}

getPapershift();