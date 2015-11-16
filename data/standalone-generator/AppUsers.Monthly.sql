INSERT INTO appusage
WITH dayzero AS (
  SELECT MAX(balanceon) AS dayzero
    FROM accountbalances
)
SELECT accountid, dayzero
  FROM accounts, dayzero
  WHERE random() < .4;
