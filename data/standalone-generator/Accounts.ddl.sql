CREATE TABLE accounts (
  accountid bigint,
  customerid bigint,
  label varchar
) DISTRIBUTED BY (accountid);

CREATE TABLE accountbalances (
  accountid bigint,
  balanceon date,
  balance decimal(10,2),
  creditlimit int
) DISTRIBUTED BY (accountid);
