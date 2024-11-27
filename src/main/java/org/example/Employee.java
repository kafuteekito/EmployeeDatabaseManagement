package org.example;

import java.util.Date;

public class Employee {
    private int id;
    private String name;
    private String position;
    private double salary;
    private Date hire_date;

    public Employee(int id, String name, String position, double salary, Date hire_Date) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.hire_date = hire_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name=" + name +
                ", position=" + position +
                ", salary=" + salary +
                ", hire_date=" + hire_date +
                '}';
    }
}
