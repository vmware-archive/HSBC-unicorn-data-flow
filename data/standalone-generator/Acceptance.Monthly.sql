INSERT INTO offervouchers (voucherid, acceptedat, expiresat)
WITH dayzero AS (
  SELECT MAX(balanceon) AS dayzero
    FROM accountbalances
),
availablevouchers AS (
  SELECT voucherid
    FROM offervouchers
    GROUP BY voucherid
    HAVING MAX(issuedat) IS NOT NULL
      AND MAX(acceptedat) IS NULL
      AND MAX(expiresat) IS NULL
),
acceptedvouchers AS (
 SELECT voucherid, (random() < .4) AS accepted
   FROM availablevouchers
)
SELECT voucherid, CASE WHEN accepted THEN dayzero ELSE NULL END, CASE WHEN NOT accepted THEN dayzero ELSE NULL END
  FROM dayzero, acceptedvouchers

;
