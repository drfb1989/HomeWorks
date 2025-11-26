CREATE TABLE customer (
                          id BIGSERIAL PRIMARY KEY,
                          full_name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          social_security_number VARCHAR(50) NOT NULL UNIQUE
);
