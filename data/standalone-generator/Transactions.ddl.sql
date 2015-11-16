CREATE TABLE transactions (
  accountid bigint,
  transactedon date,
  amount decimal(10,2),
  description varchar(64)  
) DISTRIBUTED BY (accountid)
; 
