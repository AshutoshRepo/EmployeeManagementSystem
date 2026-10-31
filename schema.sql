CREATE DATABASE IF NOT EXISTS ems_db;
USE ems_db;

CREATE TABLE IF NOT EXISTS employees (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    email VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY email (email)
    );

-- Sample data (optional): delete this block if you want an empty table
INSERT INTO employees (first_name, last_name, department, salary, email) VALUES
('Rahul', 'Sharma', 'Engineering', 65000.00, 'rahul.sharma@example.com'),
('Priya', 'Verma', 'HR', 48000.00, 'priya.verma@example.com'),
('Amit', 'Patel', 'Finance', 55000.00, 'amit.patel@example.com'),
('Neha', 'Gupta', 'Marketing', 52000.00, 'neha.gupta@example.com'),
('Karan', 'Mehta', 'Engineering', 70000.00, 'karan.mehta@example.com');