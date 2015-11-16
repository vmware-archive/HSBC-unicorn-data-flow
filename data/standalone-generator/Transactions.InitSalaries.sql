INSERT INTO transactions
WITH salaryday AS (
  SELECT MAX(balanceon) + INTERVAL '1 day' AS salaryday
    FROM accountbalances
),
inputamounts AS (
  SELECT accountid, GREATEST(creditlimit*((random()*1.5)+.5), random()*500) AS inputamount
    FROM accountbalances
),
roundedamounts AS (
  SELECT accountid, CASE WHEN inputamount < 450 THEN inputamount::int - inputamount::int%50 ELSE inputamount END AS roundedamount
    FROM inputamounts
    ORDER BY inputamount
)
SELECT accountid, salaryday, roundedamount,
    CASE WHEN roundedamount < 450 THEN 'Inter Account Transfer' ELSE 'Salary' END AS description
  FROM roundedamounts, salaryday
;
