package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/Employee";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (
                Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Connected to Database");

            while(true) {
                System.out.println("\nEmployee Management System");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employee by ID");
                System.out.println("3. View All Employees");
                System.out.println("4. Update Employee");
                System.out.println("5. Delete Employee");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        addEmployee(conn, scanner);
                        break;
                    case 2:
                        System.out.print("Enter Employee ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        getEmployeeById(conn, id);
                        break;
                    case 3:
                        retrieveEmployees(conn);
                        break;
                    case 4:
                        updateEmployee(conn, scanner);
                        break;
                    case 5:
                        System.out.print("Enter Employee ID to delete: ");
                        int delid = scanner.nextInt();
                        scanner.nextLine();
                        deleteEmployee(conn, delid);
                        break;
                    case 6:
                        System.out.println("Exiting Employee Management System. Goodbye!");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to create new employee
    public static void addEmployee(Connection conn, Scanner scanner) {
        String createQuery = "insert into Employee (name, position, salary, hire_date) values (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {

            System.out.print("Enter employee name: ");
            String name = scanner.nextLine();

            System.out.print("Enter employee position: ");
            String position = scanner.nextLine();

            System.out.print("Enter employee salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter hire date (YYYY-MM-DD): ");
            String hireDate = scanner.next(); // Expecting input in YYYY-MM-DD format
            Date sqlHireDate = Date.valueOf(hireDate);

            // Set values in PreparedStatement
            pstmt.setString(1, name);
            pstmt.setString(2, position);
            pstmt.setDouble(3, salary);
            pstmt.setDate(4, sqlHireDate);

            // Execute update
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Employee added successfully.");
                // auto-increment function to ID
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        System.out.println("Generated Employee ID: " + generatedKeys.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to get an employee by ID
    public static void getEmployeeById(Connection conn, int id) {
        String getQuery = "select * from Employee where id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(getQuery)) {
            // Set the ID parameter
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Retrieve employee details from ResultSet
                    int employeeId = rs.getInt("id");
                    String name = rs.getString("name");
                    String position = rs.getString("position");
                    double salary = rs.getDouble("salary");
                    Date hireDate = rs.getDate("hire_date");

                    // Print employee details
                    System.out.printf("Employee ID: %d%n", employeeId);
                    System.out.printf("Name: %s%n", name);
                    System.out.printf("Position: %s%n", position);
                    System.out.printf("Salary: %.2f%n", salary);
                    System.out.printf("Hire Date: %s%n", hireDate);
                } else {
                    System.out.println("Employee not found with ID: " + id);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to retrieve all employees
    public static void retrieveEmployees(Connection conn) {
        String retrieveQuery = "select * from Employee";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(retrieveQuery)) {

            System.out.println("Employers:");
            System.out.printf("%-5s%-15s%-15s%-15s%-15s%n", "ID", "Name", "Position", "Salary", "Hire Date");
            System.out.println("-----------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf(
                        "%-5s%-15s%-15s%-15.2f%-15s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getDouble("salary"),
                        rs.getDate("hire_date")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateEmployee(Connection conn, Scanner scanner) {
        String updateQuery = "update Employee set name = ?, position = ?, salary = ?, hire_date = ? where id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
            System.out.print("Enter the Employee ID to update: ");
            int employeeId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter new name: ");
            String name = scanner.nextLine();

            System.out.print("Enter new position: ");
            String position = scanner.nextLine();

            System.out.print("Enter new salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter new hire date (YYYY-MM-DD): ");
            String hireDateInput = scanner.nextLine();

            // Prepare the query parameters
            pstmt.setString(1, name.isEmpty() ? null : name);
            pstmt.setString(2, position.isEmpty() ? null : position);
            pstmt.setDouble(3, salary == 0 ? Double.NaN : salary);

            java.sql.Date sqlHireDate = hireDateInput.isEmpty() ? null : java.sql.Date.valueOf(hireDateInput);
            pstmt.setDate(4, sqlHireDate);

            pstmt.setInt(5, employeeId);

            // Execute the update query
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Employee details updated successfully.");
            } else {
                System.out.println("Employee with ID " + employeeId + " not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    // Function to delete employee by ID
    public static void deleteEmployee(Connection conn, int id) {
        String deleteQuery = "delete from Employee where id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {

            // Set the ID of the employee to delete
            pstmt.setInt(1, id);

            // Execute the delete query
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Employee with ID " + id + " has been successfully deleted.");
            } else {
                System.out.println("Employee with ID " + id + " not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



