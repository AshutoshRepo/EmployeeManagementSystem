package dao;
import java.util.List;
import model.Employee;
import java.sql.SQLException;

public interface EmployeeDAO {
    int addEmployee(Employee employee) throws SQLException;
    List<Employee> viewAllEmployees() throws SQLException;
    Employee findEmployee(int id) throws SQLException;
    boolean updateDepartment(int id,String department) throws SQLException;
    boolean updateSalary(int id,double salary) throws SQLException;
    boolean updateEmail(int id,String email) throws SQLException;
    boolean updateAll(int id,String department,double salary,String email) throws SQLException;
    boolean deleteEmployee(int id) throws SQLException;
}