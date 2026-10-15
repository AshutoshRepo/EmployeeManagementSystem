package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
   private Employee employee;
   @BeforeEach // Runs before EVERY test, so each test gets a fresh Employee
    void setUp()
   {
       employee=new Employee(1,"Ashutosh","Singh","SDET",15000.0,"ashu@gmail.com");
       System.out.println("Test Started");
   }
    // ================= Batch 1: Constructor and getters =================
    // Rule: no setter is called here. We only read what setUp() already stored.
   @Test
    void constructor_setsId()
   {
       assertEquals(1,employee.getId());
   }
   @Test
    void constructor_setsFirstName()
   {
       assertEquals("Ashutosh",employee.getFirstName());
   }
   @Test
    void constructor_setsLastName()
   {
       assertEquals("Singh",employee.getLastName());
   }
   @Test
    void constructor_setsDepartment()
   {
       assertEquals("SDET",employee.getDepartment());
   }
   @Test
    void constructor_setsSalary()
   {
       assertEquals(15000.0,employee.getSalary(),0.001);
   }
   @Test
    void constructor_setsEmail()
   {
       assertEquals("ashu@gmail.com",employee.getEmail());
   }
    // ================= Batch 2: getFullName() and setters =================
    // Rule: call the setter first (Act), then read with the getter (Assert).
    @Test
    void setId_updatesId()
    {
        employee.setId(2);
        assertEquals(2,employee.getId());
    }
    @Test
    void getFullName_returnsFirstAndLastName()
    {
        assertEquals("Ashutosh Singh",employee.getFullName());
    }
    @Test
    void setFirstName_updatesFirstName()
    {
        employee.setFirstName("Ashu");
        assertEquals("Ashu",employee.getFirstName());
    }
    @Test
    void setLastName_updatesLastName()
    {
        employee.setLastName("Kumar");
        assertEquals("Kumar",employee.getLastName());
    }
    @Test
    void setDepartment_updatesDepartment()
    {
        employee.setDepartment("DevOps");
        assertEquals("DevOps",employee.getDepartment());
    }
    @Test
    void setSalary_updatesSalary()
    {
        employee.setSalary(25000);
        assertEquals(25000,employee.getSalary(),0.001);
    }
    @Test
    void setEmail_updatesEmail()
    {
        employee.setEmail("ashu@outlook.com");
        assertEquals("ashu@outlook.com",employee.getEmail());
    }
    // ================= Batch 3: toString() and short constructor =================
    @Test
    void toString_containsId()
    {
        assertTrue(employee.toString().contains("Id: 1"));
    }
    @Test
    void toString_containsFirstName()
    {
        assertTrue(employee.toString().contains("Name: Ashutosh"));
    }
    @Test
    void toString_containsLastName()
    {
        assertTrue(employee.toString().contains("Singh"));
    }
    @Test
    void toString_containsDepartment()
    {
        assertTrue(employee.toString().contains("Department: SDET"));
    }
    @Test
    void toString_containsSalary()
    {
        assertTrue(employee.toString().contains("Salary: 15000.0"));
    }
    @Test
    void toString_containsEmail()
    {
        assertTrue(employee.toString().contains("Email: ashu@gmail.com"));
    }
    // Checks an employee created with the short constructor has the default id (0)
    // because the id is only assigned later by the database
    @Test
    void shortConstructor_idIsDefault() {
        Employee e = new Employee("Rahul", "Verma", "HR", 30000.0, "rahul@gmail.com");
        assertEquals(0, e.getId());
    }
}