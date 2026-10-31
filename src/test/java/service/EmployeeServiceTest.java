package service;

import dao.EmployeeDAO;
import exception.DuplicateEmailException;
import exception.EmployeeNotFoundException;
import exception.InvalidInputException;
import model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    EmployeeDAO employeeDAO;
    @InjectMocks
    EmployeeService employeeService;

    //addEmployee Test
    // Checks addEmployee() rejects a blank first name and never reaches the database
    @Test
    void addEmployee_blankFirstName_throwsInvalidInputException() throws SQLException
    {
        Employee emp = new Employee("","Singh","SDET",15000.0,"ashu@gmail.com");
        assertThrows(InvalidInputException.class,()->employeeService.addEmployee(emp));
        verify(employeeDAO,never()).addEmployee(any());
    }
    // Checks addEmployee() rejects a blank last name and never reaches the database
    @Test
    void addEmployee_blankLastName_throwsInvalidInputException() throws SQLException
    {
        Employee emp = new Employee("Ashutosh","","SDET",15000.0,"ashu@gmail.com");
        assertThrows(InvalidInputException.class,()->employeeService.addEmployee(emp));
        verify(employeeDAO,never()).addEmployee(any());
    }
    // Checks addEmployee() rejects a blank department and never reaches the database
    @Test
    void addEmployee_blankDepartment_throwsInvalidInputException() throws SQLException
    {
        Employee emp = new Employee("Ashutosh","Singh","",15000.0,"ashu@gmail.com");
        assertThrows(InvalidInputException.class,()->employeeService.addEmployee(emp));
        verify(employeeDAO,never()).addEmployee(any());
    }
    // Checks addEmployee() rejects the zero salary and never reaches the database
    @Test
    void addEmployee_zeroSalary_throwsInvalidInputException() throws SQLException
    {
        Employee emp = new Employee("Ashutosh","Singh","SDET",0.0,"ashu@gmail.com");
        assertThrows(InvalidInputException.class,()->employeeService.addEmployee(emp));
        verify(employeeDAO,never()).addEmployee(any());
    }
    // Checks addEmployee() rejects the negative salary and never reaches the database
    @Test
    void addEmployee_negativeSalary_throwsInvalidInputException() throws SQLException
    {
        Employee emp = new Employee("Ashutosh","Singh","SDET",-10000.0,"ashu@gmail.com");
        assertThrows(InvalidInputException.class,()->employeeService.addEmployee(emp));
        verify(employeeDAO,never()).addEmployee(any());
    }
    // Checks addEmployee() rejects a blank email and never reaches the database
    @Test
    void addEmployee_blankEmail_throwsInvalidInputException() throws SQLException
    {
        Employee emp = new Employee("Ashutosh","Singh","SDET",15000.0,"");
        assertThrows(InvalidInputException.class,()->employeeService.addEmployee(emp));
        verify(employeeDAO,never()).addEmployee(any());
    }
    // Checks addEmployee() rejects a invalid email and never reaches the database
    @Test
    void addEmployee_invalidEmailFormat_throwsInvalidInputException() throws SQLException
    {
        Employee emp = new Employee("Ashutosh","Singh","SDET",15000.0,"ashugmail.com");
        assertThrows(InvalidInputException.class,()->employeeService.addEmployee(emp));
        verify(employeeDAO,never()).addEmployee(any());
    }
    // Checks addEmployee() rejects the duplicate email
    @Test
    void addEmployee_duplicateEmail_throwsDuplicateEmailException() throws SQLException
    {
        Employee emp = new Employee("Ashutosh","Singh","SDET",15000.0,"ashu@gmail.com");
        when(employeeDAO.addEmployee(any())).thenThrow(new SQLIntegrityConstraintViolationException());
        //.thenThrow(new SQLIntegrityConstraintViolationException()) makes the fake behave like MySQL rejecting a duplicate email.
        assertThrows(DuplicateEmailException.class,()->employeeService.addEmployee(emp));
        verify(employeeDAO).addEmployee(emp);
    }

    // findEmployee Test
    // Checks findEmployee() rejects id 0 and never reaches the database
    @Test
    void findEmployee_zeroId_throwsInvalidInputException() throws SQLException
    {
        assertThrows(InvalidInputException.class, () -> employeeService.findEmployee(0));
        verify(employeeDAO, never()).findEmployee(anyInt());
    }
    // Checks findEmployee() throws EmployeeNotFoundException when the DAO finds nothing (returns null)
    @Test
    void findEmployee_unknownId_throwsEmployeeNotFoundException() throws SQLException
    {
        when(employeeDAO.findEmployee(55)).thenReturn(null);
        assertThrows(EmployeeNotFoundException.class,()->employeeService.findEmployee(55));
        verify(employeeDAO).findEmployee(55);
    }

    // Test for updateDepartment, updateSalary, updateEmail, updateAll
    // Checks updateDepartment() rejects a blank department and never reaches the database
    @Test
    void updateDepartment_blankDepartment_throwsInvalidInputException() throws SQLException
    {
        assertThrows(InvalidInputException.class,()->employeeService.updateDepartment(1,""));
        verify(employeeDAO,never()).updateDepartment(anyInt(),anyString());
    }
    // Checks updateDepartment() throws EmployeeNotFoundException when the DAO updates no row (returns false)
    @Test
    void updateDepartment_unknownId_throwsEmployeeNotFoundException() throws SQLException
    {
        when(employeeDAO.updateDepartment(99,"DevOps")).thenReturn(false);
        assertThrows(EmployeeNotFoundException.class,()->employeeService.updateDepartment(99,"DevOps"));
        verify(employeeDAO).updateDepartment(99,"DevOps");
    }
    // Checks updateSalary() rejects a negative salary and never reaches the database
    @Test
    void updateSalary_negativeSalary_throwsInvalidInputException() throws SQLException
    {
        assertThrows(InvalidInputException.class,()->employeeService.updateSalary(1,-15000.0));
        verify(employeeDAO,never()).updateSalary(anyInt(),anyInt());
    }
    // Checks updateEmail() rejects an email with no domain ending (like .com) and never reaches the database
    @Test
    void updateEmail_invalidEmail_throwsInvalidInputException() throws SQLException
    {
        assertThrows(InvalidInputException.class,()->employeeService.updateEmail(1,"ashu@gmail"));
        verify(employeeDAO,never()).updateEmail(anyInt(),anyString());
    }
    // Checks updateEmail() converts the database's duplicate-email error into DuplicateEmailException
    @Test
    void updateEmail_duplicateEmail_throwsDuplicateEmailException() throws SQLException
    {
        when(employeeDAO.updateEmail(1,"ashu@gmail.com")).thenThrow(new  SQLIntegrityConstraintViolationException());
        assertThrows(DuplicateEmailException.class,()->employeeService.updateEmail(1,"ashu@gmail.com"));
        verify(employeeDAO).updateEmail(1,"ashu@gmail.com");
    }
    // Checks updateAll() throws EmployeeNotFoundException when the DAO updates no row (returns false)
    @Test
    void updateAll_unknownId_throwsEmployeeNotFoundException() throws SQLException
    {
        when(employeeDAO.updateAll(99,"DevOps",15000.0,"ashu@gmail.com")).thenReturn(false);
        assertThrows(EmployeeNotFoundException.class,()->employeeService.updateAll(99,"DevOps",15000.0,"ashu@gmail.com"));
        verify(employeeDAO).updateAll(99,"DevOps",15000.0,"ashu@gmail.com");
    }
    // Checks deleteEmployee() rejects id 0 and never reaches the database
    @Test
    void deleteEmployee_zeroId_throwsInvalidInputException() throws SQLException
    {
        assertThrows(InvalidInputException.class,()->employeeService.deleteEmployee(0));
        verify(employeeDAO,never()).deleteEmployee(anyInt());
    }
    // Checks deleteEmployee() throws EmployeeNotFoundException when the DAO deletes no row (returns false)
    @Test
    void deleteEmployee_unknownId_throwsEmployeeNotFoundException() throws SQLException
    {
        when(employeeDAO.deleteEmployee(99)).thenReturn(false);
        assertThrows(EmployeeNotFoundException.class,()->employeeService.deleteEmployee(99));
        verify(employeeDAO).deleteEmployee(99);
    }
}
