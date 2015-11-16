INSERT INTO transactions
WITH salaryday AS (
  SELECT MAX(balanceon) + INTERVAL '1 day' AS salaryday
    FROM accountbalances
),
precedent AS (
  SELECT accountid, amount, description
    FROM transactions, salaryday
    WHERE transactedon = salaryday - INTERVAL '1 month'
      AND amount > 0
)
SELECT accountid, salaryday,
    CASE WHEN random() < 0.02 THEN amount * (1 + random()*.2) ELSE amount END AS amount, description
  FROM precedent,salaryday
;
