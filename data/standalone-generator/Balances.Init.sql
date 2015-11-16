INSERT INTO accountbalances
WITH limits AS (
  SELECT accountid, CASE WHEN random() < .2 THEN -1 ELSE 1 END*(accountid%10)*1000 AS creditlimit
    FROM accounts
)
SELECT accountid, '20140624',
    CASE WHEN random() < .2 THEN creditlimit*(.8 + random()*.4)*-1 ELSE creditlimit*(.2 + random()*.4)*-1 END AS balance,
    creditlimit
  FROM limits

;
