package service;
import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import exception.DuplicateEmailException;
import exception.EmployeeNotFoundException;
import model.Employee;
import exception.InvalidInputException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

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
    // Throws InvalidInputException if the text is null or blank
    private void validateText(String value, String fieldName) throws InvalidInputException {
        if (value == null || value.isBlank()) {
            throw new InvalidInputException(fieldName + " cannot be empty");
        }
    }
    // Throws InvalidInputException if salary is 0 or negative
    private void validateSalary(double salary) throws InvalidInputException
    {
        if (salary<=0)
        {
            throw new InvalidInputException("Salary cannot be 0 or negative");
        }
    }
    // Throws InvalidInputException if id is 0 or negative
    private void validateId(int id) throws InvalidInputException
    {
        if (id <= 0)
        {
            throw new InvalidInputException("Id cannot be 0 or negative");
        }
    }
    // Throws InvalidInputException if email is blank or not in a valid format
    /*
     * Regex notes:
     * regex = a pattern that describes the shape of a text
     * ^ and $      -> text must start / end exactly here
     * [A-Za-z0-9]  -> one character from this set
     * +            -> one or more of the previous thing
     * {2,}         -> at least 2 of the previous thing
     * \\.          -> a real dot (plain . means any character, \\ because Java needs it doubled)
     * Pattern here: something @ domain . 2+ letters  (ashu@gmail.com)
     * matches() returns true when the text fits the pattern, so use ! to reject bad ones
     */
    private void validateEmail(String email) throws InvalidInputException
    {
        if (email == null || email.isBlank())
        {
            throw new InvalidInputException("Email cannot be empty");
        }
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
        {
            throw new InvalidInputException("Enter a valid email address");
        }
    }

    public void addEmployee(Employee employee) throws InvalidInputException,DuplicateEmailException
    {
        validateText(employee.getFirstName(),"First Name");
        validateText(employee.getLastName(),"Last Name");
        validateText(employee.getDepartment(),"Department");
        validateSalary(employee.getSalary());
        validateEmail(employee.getEmail());
        try
        {
            int id=employeeDAO.addEmployee(employee);
            employee.setId(id);
            System.out.println("Employee " + employee.getFullName() + " added successfully with ID: " + id);
        }
        /*
         * SQLIntegrityConstraintViolationException notes:
         * thrown when MySQL rejects data that breaks a constraint (UNIQUE, NOT NULL, FOREIGN KEY)
         * it is a child of SQLException, so it must be caught BEFORE SQLException
         * (specific catch first, general catch last, or the compiler gives an error)
         * here the only constraint that can fail is UNIQUE email, so we turn it into DuplicateEmailException
         * if more constraints are added later, check which one failed before showing this message
         */
        catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicateEmailException("This email is already registered");
        }
        catch (SQLException e) {
            System.out.println("Something Went Wrong. Please Try Again");
            e.printStackTrace();
        }
    }
    public void viewAllEmployees()
    {
        try {
            List<Employee> employeeList=employeeDAO.viewAllEmployees();
            if(employeeList.isEmpty())
            {
                System.out.println("No employee added yet");
            }
            else
            {
                for(Employee e:employeeList)
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
        catch (SQLException e)
        {
            System.out.println("Something Went Wrong. Please Try Again");
            e.printStackTrace();
        }
    }
    public void findEmployee(int id) throws InvalidInputException, EmployeeNotFoundException
    {
        validateId(id);
        try {
            Employee emp=employeeDAO.findEmployee(id);
            if(emp==null)
            {
                throw new EmployeeNotFoundException("No employee found with ID: " + id);
            }
            dash();
            System.out.println("ID: "+emp.getId());
            System.out.println("Full Name: "+emp.getFullName());
            System.out.println("Department: "+emp.getDepartment());
            System.out.println("Salary: "+emp.getSalary());
            System.out.println("Email: "+emp.getEmail());
        }
        catch (SQLException e)
        {
            System.out.println("Something Went Wrong. Please Try Again");
            e.printStackTrace();
        }
    }
    public void updateDepartment(int id,String department) throws InvalidInputException
    {
        validateId(id);
        validateText(department,"Department");
        try {
           boolean status=employeeDAO.updateDepartment(id,department);
           if(status)
           {
               System.out.println("Department updated successfully");
           }
           else
           {
               System.out.println("No employee found with ID: " + id);
           }
        }
        catch (SQLException e)
        {
            System.out.println("Something Went Wrong. Please Try Again");
            e.printStackTrace();
        }
    }
    public void updateSalary(int id, double salary) throws InvalidInputException
    {
        validateId(id);
        validateSalary(salary);
        try {
            boolean status=employeeDAO.updateSalary(id,salary);
            if(status)
            {
                System.out.println("Salary updated successfully");
            }
            else
            {
                System.out.println("No employee found with ID: " + id);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Something Went Wrong. Please Try Again");
            e.printStackTrace();
        }
    }
    public void updateEmail(int id, String email) throws InvalidInputException,DuplicateEmailException
    {
        validateId(id);
        validateEmail(email);
       try {
           boolean status=employeeDAO.updateEmail(id,email);
           if(status)
           {
               System.out.println("Email updated successfully");
           }
           else
           {
               System.out.println("No employee found with ID: " + id);
           }
       }
       catch (SQLIntegrityConstraintViolationException e)
       {
           throw new DuplicateEmailException("This email is already registered");
       }
       catch (SQLException e)
       {
           System.out.println("Something Went Wrong. Please Try Again");
           e.printStackTrace();
       }
    }
    public void updateAll(int id, String department, double salary, String email) throws InvalidInputException, DuplicateEmailException
    {
        validateId(id);
        validateText(department,"Department");
        validateSalary(salary);
        validateEmail(email);
        try {
            boolean status=employeeDAO.updateAll(id,department,salary,email);
            if(status)
            {
                System.out.println("Employee details updated successfully");
            }
            else
            {
                System.out.println("No employee found with ID: " + id);
            }
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            throw new DuplicateEmailException("This email is already registered");
        }
        catch (SQLException e)
        {
            System.out.println("Something Went Wrong. Please Try Again");
            e.printStackTrace();
        }
    }
    public void deleteEmployee(int id) throws InvalidInputException, EmployeeNotFoundException
    {
        validateId(id);
        try {
            boolean status=employeeDAO.deleteEmployee(id);
            if (!status)
            {
                throw new EmployeeNotFoundException("No employee found with ID: " + id);
            }
            System.out.println("Employee deleted successfully");
        }
        catch (SQLException e)
        {
            System.out.println("Something Went Wrong. Please Try Again");
            e.printStackTrace();
        }
    }

}