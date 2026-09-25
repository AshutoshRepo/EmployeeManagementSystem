package dao;
import java.util.List;
import model.Employee;
import java.sql.SQLException;

public interface EmployeeDAO {
    int addEmployee(Employee employee) throws SQLException;
    List<Employee> viewAllEmployees() throws SQLException;
    Employee findEmployee(int id) throws SQLException;
    void updateDepartment(int id,String department) throws SQLException;
    void updateSalary(int id,double salary) throws SQLException;
    void updateEmail(int id,String email) throws SQLException;
    void updateAll(int id,String department,double salary,String email) throws SQLException;
    boolean deleteEmployee(int id) throws SQLException;
}