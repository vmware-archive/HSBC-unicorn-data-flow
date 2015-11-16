CREATE TABLE firstnames (
  nameid int,
  firstname varchar
) DISTRIBUTED RANDOMLY;

CREATE TABLE surnames (
  nameid int,
  surname varchar
) DISTRIBUTED RANDOMLY;
