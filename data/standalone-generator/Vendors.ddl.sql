CREATE TABLE vendors (
  vendorid int,
  label varchar
) DISTRIBUTED BY (vendorid);
