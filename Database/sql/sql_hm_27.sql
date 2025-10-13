CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50),
                       age INT,
                       email VARCHAR(100)
);
INSERT INTO users (name, age, email) VALUES
                                         ('John', 30, 'john@example.com'),
                                         ('Alice', 25, 'alice@example.com'),
                                         ('Bob', 35, 'bob@example.com');

DELETE FROM users WHERE name = 'Bob';