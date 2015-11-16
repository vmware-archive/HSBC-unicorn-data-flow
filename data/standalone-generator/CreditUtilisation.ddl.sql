CREATE OR REPLACE VIEW creditutilisation AS

WITH monthlyutilisation AS (
  SELECT balanceon, SUM(creditlimit) AS totalcredit, SUM(0 - balance) FILTER (WHERE balance < 0) AS utilisedcredit
    FROM accountbalances
    GROUP BY balanceon
    ORDER BY balanceon
),
monthsthatmatter AS (
  SELECT MAX(balanceon) AS lastbalance
    FROM monthlyutilisation
)
SELECT monthlyutilisation.*
  FROM monthlyutilisation, monthsthatmatter
--  WHERE balanceon = lastbalance OR balanceon = lastbalance - INTERVAL '1 year'
