INSERT INTO offervouchers
WITH salaryday AS (
  SELECT MAX(balanceon) + INTERVAL '1 day' AS salaryday
    FROM accountbalances
),
salarycomparison AS (
  SELECT accountid, amount AS currentsalary, LAG(amount) OVER(PARTITION BY accountid ORDER BY transactedon) AS previoussalary
    FROM transactions, salaryday
    WHERE amount > 0
      AND transactedon > salaryday - INTERVAL '1 month' - INTERVAL '1 day'
),
firstoffers AS (
  SELECT COUNT(*) AS offercount
    FROM offervouchers
)
SELECT random()*1000000, 1, accountid - MIN(accountid) OVER() + 32767*(offercount = 0)::int
  FROM salarycomparison, firstoffers
  WHERE currentsalary - previoussalary > previoussalary * .05
    AND currentsalary - previoussalary > 250;
