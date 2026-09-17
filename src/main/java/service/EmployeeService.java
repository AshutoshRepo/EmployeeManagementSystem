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
    public void updateDepartment(int id,String department)
    {
        for(Employee e:employees)
        {
            if(e.getId()==id)
            {
                e.setDepartment(department);
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
                return;
            }
        }
        System.out.println("Employee not found");
    }

}