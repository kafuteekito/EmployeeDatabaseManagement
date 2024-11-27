# Employee Management System

## Project Description
The Employee Management System is a Java-based application designed to manage employee records. This system provides an intuitive interface for performing CRUD (Create, Read, Update, Delete) operations on an Employee table in a PostgreSQL database using JDBC. The application ensures seamless management of employee data, such as their name, position, salary, and hire date, with robust error handling and user-friendly input validation.

## Features
1. **Add Employee**: Create new employee records.
2. **View Employee by ID**: Retrieve specific employee details using their unique ID.
3. **View All Employees**: List all employee records from the database.
4. **Update Employee**: Modify existing employee details.
5. **Delete Employee**: Remove an employee record from the database.
6. **Exit Application**: Exit the management system gracefully.

## Requirements
- Java Development Kit (JDK) 8 or higher
- PostgreSQL 9.6 or higher
- JDBC Driver for PostgreSQL
- IDE or text editor (e.g., IntelliJ IDEA, Eclipse, Visual Studio Code)

## Setup Instructions

### Step 1: Database Setup
1. Install and configure PostgreSQL.
2. Create a database named `Employee`.
3. Execute the following SQL script to create the `employee` table:

```sql
CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    salary DOUBLE PRECISION NOT NULL,
    hire_date DATE NOT NULL
);
```

### Step 2: Project Setup
1. Clone the repository or download the source files.
2. Open the project in your preferred IDE.
3. Configure the PostgreSQL database connection in the `Main` class:
   - Update the `URL`, `USERNAME`, and `PASSWORD` constants to match your PostgreSQL setup:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/Employee";
private static final String USERNAME = "postgres";
private static final String PASSWORD = "your_password";
```

4. Build and compile the project.

### Step 3: Run the Application
1. Run the `Main` class to start the application.
2. Follow the on-screen prompts to interact with the Employee Management System.

## Usage Examples
![image](https://github.com/user-attachments/assets/3e0f5fdb-6728-4a3c-b02d-2499ffa83032)

### Adding an Employee
![image](https://github.com/user-attachments/assets/e263868d-8bc6-4cc0-ba4c-4f8b9db61c15)

### Viewing an Employee by ID
![image](https://github.com/user-attachments/assets/edadcec5-a6b9-40d1-93bc-2ecbdafa43c8)


### Viewing all Employers
![image](https://github.com/user-attachments/assets/46b6ab47-f091-4768-8e51-f13fb87677f6)

### Updating an Employee
![image](https://github.com/user-attachments/assets/ea39aed1-c274-4e23-bbdc-5e1ec09c8685)


### Deleting an Employee
![image](https://github.com/user-attachments/assets/607a2f71-45cd-4627-9423-afa49a5299b9)

### After all operations
![image](https://github.com/user-attachments/assets/401571d7-496e-4ddf-aacb-675322e68122)

