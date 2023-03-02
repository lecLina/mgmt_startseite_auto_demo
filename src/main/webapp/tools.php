<?php

class usershift
{
  public $startdate;
  public $shiftid;
  public $username;
}

function cmpDate($a, $b) {
  if ($a->startdate == $b->startdate) {
      return 0;
  }
  return ($a->startdate < $b->startdate) ? -1 : 1;
}
