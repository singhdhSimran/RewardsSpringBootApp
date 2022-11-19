INSERT INTO customers (customer_id, first_name, last_name) VALUES (1, 'Simranjeet', 'Singh');
INSERT INTO customers (customer_id, first_name, last_name) VALUES (2, 'ST', 'MN');

INSERT INTO transactions (transaction_id, execution_time, amount, customer_id) VALUES (23, {ts '2022-09-17 18:47:52.69'}, 60, 2);
INSERT INTO transactions (transaction_id, execution_time, amount, customer_id) VALUES (24, {ts '2022-10-13 17:22:46.91'}, 127, 2);
INSERT INTO transactions (transaction_id, execution_time, amount, customer_id) VALUES (25, {ts '2022-07-08 18:47:52.69'}, 51, 1);
INSERT INTO transactions (transaction_id, execution_time, amount, customer_id) VALUES (26, {ts '2022-08-14 17:22:46.91'}, 90, 1);