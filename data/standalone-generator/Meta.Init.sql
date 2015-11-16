TRUNCATE TABLE accounts;
TRUNCATE TABLE accountbalances;
TRUNCATE TABLE transactions;
TRUNCATE TABLE offervouchers;

INSERT INTO accounts

WITH surnamecount AS (
  SELECT COUNT(*) AS surnamecount
    FROM surnames
),
firstnamecount AS (
  SELECT COUNT(*) AS firstnamecount
    FROM firstnames
),
allcombos AS (
  SELECT (surnamecount.surnamecount*random())::int AS firstnameid, (firstnamecount.firstnamecount*random())::int AS surnameid,
      32768 + generate_series AS accountid
    FROM surnamecount, firstnamecount, generate_series(1,1000)
),
distinctcombos AS (
  SELECT DISTINCT *
    FROM allcombos
)
SELECT accountid, accountid, firstname||' '||surname
  FROM distinctcombos
    INNER JOIN firstnames ON distinctcombos.firstnameid = firstnames.nameid
    INNER JOIN surnames ON distinctcombos.surnameid = surnames.nameid
;
INSERT INTO accountbalances
WITH limits AS (
  SELECT accountid, CASE WHEN random() < .1 THEN -1 ELSE 1 END*(accountid%10)*1000 AS creditlimit
    FROM accounts
)
SELECT accountid, '20140624',
    CASE WHEN random() < .2 THEN creditlimit*(.8 + random()*.4)*-1 ELSE creditlimit*(random()*.3)*-1 END AS balance,
    creditlimit
  FROM limits

;
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
