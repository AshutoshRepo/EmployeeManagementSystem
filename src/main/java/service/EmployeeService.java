package service;
import java.util.List;
import java.util.ArrayList;
import model.Employee;

public class EmployeeService {
    private List<Employee> employees;
    public EmployeeService()
    {
        employees=new ArrayList<>();
    }
    public void dash()
    {
        System.out.println("---------------------------------------------------------");
    }
    public void addEmployee(Employee employee)
    {
        for(Employee e:employees)
        {
            if(e.getId()==employee.getId())
            {
                System.out.println("UserId already exist");
                return;
            }
        }
        employees.add(employee);
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

}