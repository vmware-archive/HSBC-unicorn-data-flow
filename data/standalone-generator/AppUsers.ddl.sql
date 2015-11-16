CREATE TABLE appusage (
  accountid bigint,
  usageon date
) DISTRIBUTED RANDOMLY;

CREATE VIEW appusagestats AS

SELECT accountbalances.accountid, balanceon, usageon IS NOT NULL AS appuser,
    (LAG(usageon, 1) OVER (PARTITION BY appusage.accountid ORDER BY usageon) = usageon - INTERVAL '1 month') AS secondmonth,
    (LAG(usageon, 2) OVER (PARTITION BY appusage.accountid ORDER BY usageon) = usageon - INTERVAL '2 months') AS thirdmonth
  FROM accountbalances LEFT JOIN appusage ON appusage.accountid = accountbalances.accountid
    AND balanceon = usageon
  ORDER BY accountbalances.accountid, balanceon;
