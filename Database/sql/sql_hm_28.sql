
--  Create database
CREATE DATABASE sales_database;


--  Create table
CREATE TABLE IF NOT EXISTS sales (
                                     id SERIAL PRIMARY KEY,
                                     product VARCHAR(100) NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    quantity INT NOT NULL
    );

--  Seed data
INSERT INTO sales (product, price, quantity) VALUES
                                                 ('Laptop', 1000, 5),
                                                 ('Phone', 700, 3),
                                                 ('Tablet', 500, 2),
                                                 ('Printer', 300, 4);

--  Select all rows
SELECT * FROM sales ORDER BY id;

--  First two rows (deterministic order)
SELECT * FROM sales ORDER BY id ASC LIMIT 2;

--  Total revenue (price * quantity across all rows)
SELECT SUM(price * quantity) AS total_revenue FROM sales;

--  Group by product: total qty and average unit price
SELECT
    product,
    SUM(quantity)        AS total_quantity,
    AVG(price)           AS avg_unit_price
FROM sales
GROUP BY product
ORDER BY product;

