INSERT INTO employee (id, first_name, last_name, email, birth_date, salary, manager_id, company_id)
    VALUES (1, 'First1', 'Last1', 'first1@gmail.com', '1980-4-12', 7000, NULL, NULL);

INSERT INTO company (id, name, city, manager_begin_date, manager_id)
    VALUES (10, 'Company1', 'Chisinau', '2005-5-10', 1);

UPDATE employee SET company_id = 10 WHERE employee.id = 1;

INSERT INTO employee VALUES (2, 'First2', 'Last2', 'first2@mail.ru', '1990-7-4', 8000, 1, 10);
INSERT INTO employee VALUES (3, 'First3', 'Last3', 'first3@gmail.com', '1988-10-2', 5000, 1, NULL);

INSERT INTO company VALUES (11, 'Company2', 'Chisinau', '2010-1-1', 3);
UPDATE employee SET company_id = 11 WHERE employee.id = 3;

INSERT INTO employee VALUES (4, 'First4', 'Last4', 'first4@mail.ru', '1992-3-23', 6000, 3, 11),
                            (5, 'First5', 'Last5', 'first5@yahoo.com', '1994-1-6', 4500, 3, 11);

INSERT INTO employee VALUES (6, 'First6', 'Last6', 'first6@gamil.com', '1978-1-2', 7500, 2, NULL);

INSERT INTO company VALUES (12, 'Company3', 'Balti', '2011-10-10', 6);

UPDATE employee SET company_id = 12 WHERE employee.id = 6;