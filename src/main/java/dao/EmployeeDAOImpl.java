package dao;

import model.Employee;
import service.EmployeeService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBConnection;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public int addEmployee(Employee employee) throws SQLException
    {
        String sql = "INSERT INTO employees (first_name, last_name, department, salary, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1,employee.getFirstName());
            stmt.setString(2,employee.getLastName());
            stmt.setString(3,employee.getDepartment());
            stmt.setDouble(4,employee.getSalary());
            stmt.setString(5,employee.getEmail());
            stmt.executeUpdate();
            ResultSet rs=stmt.getGeneratedKeys();
            if(rs.next())
            {
                return rs.getInt(1);
            }
            else
            {
                throw new SQLException("Insert failed, no ID generated.");
            }

        }
    }

    @Override
    public List<Employee> viewAllEmployees() throws SQLException {
        String sql="select * from employees";
        List<Employee> employees=new ArrayList<>();
        try(Connection conn=DBConnection.getConnection();
        PreparedStatement stmt=conn.prepareStatement(sql);
        ResultSet rs=stmt.executeQuery())
        {
            while(rs.next())
            {
                Employee emp=new Employee(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("department"),rs.getDouble("salary"),rs.getString("email"));
                employees.add(emp);
                EmployeeService.dash();
            }

        }
        return employees;
    }

    @Override
    public Employee findEmployee(int id) throws SQLException {
        String sql="SELECT * FROM employees WHERE id=?";
        try(Connection conn=DBConnection.getConnection();
        PreparedStatement stmt=conn.prepareStatement(sql))
        {
            stmt.setInt(1,id);
        try(ResultSet rs=stmt.executeQuery())
        {

            if (rs.next())
            {
                Employee emp=new Employee(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("department"),rs.getDouble("salary"),rs.getString("email"));
                return emp;
            }
            else
            {
                return null;
            }

        }
        }
    }

    @Override
    public void updateDepartment(int id, String department) throws SQLException {

    }

    @Override
    public void updateSalary(int id, double salary) throws SQLException {

    }

    @Override
    public void updateEmail(int id, String email) throws SQLException {

    }

    @Override
    public void updateAll(int id, String department, double salary, String email) throws SQLException {

    }

    @Override
    public boolean deleteEmployee(int id) throws SQLException {
        return false;
    }
}