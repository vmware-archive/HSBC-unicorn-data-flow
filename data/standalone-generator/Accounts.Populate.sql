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
