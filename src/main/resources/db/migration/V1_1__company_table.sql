CREATE TABLE company(
    id INT,
    name VARCHAR(50) UNIQUE,
    city VARCHAR(50),
    manager_begin_date DATE,
    manager_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (manager_id) REFERENCES employee(id)
);