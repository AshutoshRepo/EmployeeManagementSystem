package service;
import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.Employee;

import java.sql.SQLException;

public class EmployeeService {
    private EmployeeDAO employeeDAO;
    public EmployeeService()
    {
        employeeDAO = new EmployeeDAOImpl();
    }
    public static void dash()
    {
        System.out.println("---------------------------------------------------------");
    }
    public void addEmployee(Employee employee)
    {
        try
        {
            int id=employeeDAO.addEmployee(employee);
            employee.setId(id);
            System.out.println("Employee added successfully and His/Her ID= "+id);
        } catch (SQLException e) {
            System.out.println("Something Went Wrong. Please Try Again");
            e.printStackTrace();
        }
    }
    public void viewAllEmployees()
    {
        if(employees.isEmpty())
        {
            System.out.println("No employee added yet");
        }
        else
        {
            for(Employee e:employees)
            {
                System.out.println("ID: "+e.getId());
                System.out.println("Full Name: "+e.getFullName());
                System.out.println("Department: "+e.getDepartment());
                System.out.println("Salary: "+e.getSalary());
                System.out.println("Email: "+e.getEmail());
                dash();
            }
        }
    }
    public void findEmployee(int id)
    {
        for (Employee e : employees)
        {
            if (e.getId() == id)
            {
                dash();
                System.out.println("ID: "+e.getId());
                System.out.println("Full Name: "+e.getFullName());
                System.out.println("Department: "+e.getDepartment());
                System.out.println("Salary: "+e.getSalary());
                System.out.println("Email: "+e.getEmail());
                return;
            }
        }
        System.out.println("Employee not found");
    }
    public void updateDepartment(int id,String department)
    {
        for(Employee e:employees)
        {
            if(e.getId()==id)
            {
                e.setDepartment(department);
                System.out.println("Department updated successfully");
                return;
            }
        }
        System.out.println("Employee not found");

    }
    public void updateSalary(int id, double salary)
    {
        for(Employee e:employees)
        {
            if(e.getId()==id)
            {
                e.setSalary(salary);
                System.out.println("Salary updated successfully");
                return;
            }
        }
        System.out.println("Employee not found");
    }
    public void updateEmail(int id, String email)
    {
        for(Employee e:employees)
        {
            if(e.getId()==id)
            {
                e.setEmail(email);
                System.out.println("Email updated successfully");
                return;
            }
        }
        System.out.println("Employee not found");
    }
    public void updateAll(int id, String department, double salary, String email)
    {
        for(Employee e:employees)
        {
            if(e.getId()==id)
            {
                e.setDepartment(department);
                e.setSalary(salary);
                e.setEmail(email);
                System.out.println("Employee details updated successfully");
                return;
            }
        }
        System.out.println("Employee not found");
    }
    public void deleteEmployee(int id)
    {
        for(Employee e:employees)
        {
            if(e.getId()==id)
            {
                employees.remove(e);
                System.out.println("Employee deleted successfully");
                return;
            }
        }
        System.out.println("Employee not found");
    }

}