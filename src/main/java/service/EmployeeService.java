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
}
