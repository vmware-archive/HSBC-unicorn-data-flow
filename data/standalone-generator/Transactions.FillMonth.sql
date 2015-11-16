INSERT INTO transactions
WITH dayzero AS (
  SELECT MAX(balanceon) AS dayzero
    FROM accountbalances
),
daterange AS (
SELECT dayzero, dayzero + INTERVAL '1 month',
    EXTRACT('day' FROM (dayzero + INTERVAL '1 Month') - dayzero)::int AS daycount
  FROM dayzero
),
transacted AS (
  SELECT accountid, SUM(amount) AS transactedsincebalance
    FROM dayzero INNER JOIN transactions
      ON transactedon > dayzero
    GROUP BY accountid
),
opening AS (
  SELECT accountid, creditlimit + balance AS openingavailable
    FROM accountbalances, dayzero
    WHERE balanceon = dayzero
),
transactionamounts AS (
  --Transactions for the month may be between .7 and 1.2 times income in the period
  SELECT opening.accountid, (transactedsincebalance)*(.9 + random()*.35) AS transactionamount
    FROM transacted INNER JOIN opening ON opening.accountid = transacted.accountid
),
transactiondistributions AS (
  SELECT accountid, transactionamount, dayzero + generate_series(1, daterange.daycount) * INTERVAL '1 day' AS transactionday, random() AS portion
    FROM transactionamounts, daterange
    WHERE transactionamount > 0
),
transactions AS (
  SELECT accountid, transactionday, portion/SUM(portion) OVER(PARTITION BY accountid)*transactionamount AS transactionvalue,
      (random()*750)::int AS vendorID
    FROM transactiondistributions
)
SELECT accountid, transactionday, 0 - transactionvalue, label
  FROM transactions INNER JOIN vendors ON vendors.vendorid = transactions.vendorid
  WHERE transactionvalue >= 1;
