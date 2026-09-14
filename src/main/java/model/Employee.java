package model;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String department;
    private double salary;
    private String email;
    /*
     constructor to initialize employee details
     public so Main and EmployeeDAO (different packages) can create Employee objects
     */
    public Employee(int id, String firstName, String lastName, String department, double salary, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString()
    {
        return "Employee Details:-\nId: "+id+"\nName: "+firstName+" "+lastName+"\nDepartment: "+department+"\nSalary: "+salary+"\nEmail: "+email;
    }
}