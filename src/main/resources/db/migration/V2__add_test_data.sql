-- predefine 1 customer
insert into customer(id, email, telephone) values(1, 'smith.daniel@gmail.com', '231-4312345');

-- predefine 2 accounts
insert into account(id, balance, type, customer_id) values(1, 1000000, 0, 1);
insert into account(id, balance, type, customer_id) values(2, 1000000, 1, 1);