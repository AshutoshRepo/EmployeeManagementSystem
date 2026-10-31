# Employee Management System

A console-based Employee Management System built in Java with JDBC and MySQL. It uses a layered design (Main, Service, DAO, Database), validates all user input, and comes with JUnit and Mockito unit tests.

## Features

- Add a new employee (first name, last name, department, salary, email)
- View all employees
- Find an employee by ID
- Update an employee's department, salary, email, or all three at once
- Delete an employee
- Input validation for names, department, salary, ID and email format
- Duplicate email detection, enforced by a UNIQUE constraint in the database
- Clear error messages using custom exceptions (`InvalidInputException`, `EmployeeNotFoundException`, `DuplicateEmailException`)

## Tech Stack

- Java 21
- Maven
- JDBC with MySQL Connector/J
- MySQL
- JUnit 5 and Mockito for testing
- JaCoCo for test coverage

## Project Structure

```
src/
├── main/java/
│   ├── Main.java                    Console menu
│   ├── dao/
│   │   ├── EmployeeDAO.java         DAO interface
│   │   └── EmployeeDAOImpl.java     JDBC implementation
│   ├── exception/
│   │   ├── DuplicateEmailException.java
│   │   ├── EmployeeNotFoundException.java
│   │   └── InvalidInputException.java
│   ├── model/
│   │   └── Employee.java            Employee data class
│   ├── service/
│   │   └── EmployeeService.java     Validation and business logic
│   └── util/
│       └── DBConnection.java        Reads db.properties, opens connections
└── test/java/
    ├── model/EmployeeTest.java
    └── service/EmployeeServiceTest.java
schema.sql                           Database and table creation
db.properties.example                Template for database credentials
pom.xml
```

## Architecture

```
Main (console menu)
  -> EmployeeService (validation, user messages)
    -> EmployeeDAO (interface)
      -> EmployeeDAOImpl (JDBC)
        -> DBConnection
          -> MySQL (ems_db)
```

- **Main** reads user input and shows the menu. It contains no business logic.
- **EmployeeService** validates the input, calls the DAO, and prints the result messages.
- **EmployeeDAO** defines the database operations. **EmployeeDAOImpl** implements them using JDBC `PreparedStatement` and try-with-resources.
- The service accepts a DAO through its constructor, so the tests can pass in a mocked DAO and run without a database.
- The DAO has no console output and does not call the service, which keeps the layers separate.

## Setup

### Requirements

- Java 21
- Maven
- MySQL

### 1. Create the database

Run the schema file. It creates the `ems_db` database, the `employees` table, and a few sample rows:

```bash
mysql -u root -p < schema.sql
```

Delete the `INSERT` block in `schema.sql` first if you want an empty table.

### 2. Add your database credentials

Copy the template and fill in your own values:

```bash
cp db.properties.example db.properties
```

```
db.url=jdbc:mysql://localhost:3306/ems_db
db.username=your_username
db.password=your_password
```

`db.properties` is listed in `.gitignore`, so your password is never committed.

### 3. Build

```bash
mvn clean compile
```

## Running the App

Open the project in IntelliJ IDEA (or any Java IDE) and run `Main`. Run it from the project root folder, because the app looks for `db.properties` there.

## Running the Tests

```bash
mvn test
```

There are 48 unit tests: 20 for the `Employee` model and 28 for `EmployeeService`. The service tests use Mockito to mock the DAO, so they do not need a database or a `db.properties` file.

## Author

Ashutosh Singh