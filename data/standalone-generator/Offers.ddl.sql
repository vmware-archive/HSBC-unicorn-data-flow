DROP TABLE IF EXISTS offervouchers;

CREATE TABLE offervouchers (
  voucherid bigint,
  offerid bigint,
  customerid bigint,
  issuedat timestamp,
  expiresat timestamp,
  acceptedat timestamp
) DISTRIBUTED BY (voucherid);

CREATE OR REPLACE VIEW offervoucherspending AS
  SELECT voucherid, offerid, customerid
    FROM offervouchers
    GROUP BY voucherid, offerid, customerid
    HAVING MAX(issuedat) IS NULL;

