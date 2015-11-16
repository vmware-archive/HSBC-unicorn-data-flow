#!/bin/bash

psql -h 74.202.154.150 -U scratch -d demo -f Meta.Init.sql

psql -h 74.202.154.150 -U scratch -d demo -f Meta.FirstMonth.sql

echo 'Enter to start scoring and offers run.'
read

psql -h 74.202.154.150 -U scratch -d demo -c 'TRUNCATE TABLE offervouchers;';

for looper in {1..12}
do
  psql -h 74.202.154.150 -U scratch -d demo -f Meta.Monthly.sql
done
