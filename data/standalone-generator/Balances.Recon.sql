INSERT INTO accountbalances
WITH dayzero AS (
  SELECT MAX(balanceon) AS dayzero
    FROM accountbalances
),
daterange AS (
SELECT MAX(transactedon) AS balanceday
  FROM transactions
),
transacted AS (
  SELECT accountid, balanceday, SUM(amount) AS transactiontotal
    FROM daterange, dayzero INNER JOIN transactions
      ON transactedon > dayzero
    GROUP BY accountid, balanceday
),
opening AS (
  SELECT accountid, balance, creditlimit
    FROM accountbalances, dayzero
    WHERE balanceon = dayzero
)
--TODO: Change a few (1%?) of credit limits - some up a few % and some halved
SELECT opening.accountid, balanceday, balance + transactiontotal AS newbalance, creditlimit
  FROM opening INNER JOIN transacted ON opening.accountid = transacted.accountid;
