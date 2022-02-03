ALTER TABLE employee ADD FOREIGN KEY (company_id)
    REFERENCES  company(id) ON DELETE SET NULL;

ALTER TABLE employee ADD FOREIGN KEY (manager_id)
    REFERENCES employee(id) ON DELETE SET NULL;