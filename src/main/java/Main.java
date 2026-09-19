import java.util.InputMismatchException;
import java.util.Scanner;
import service.EmployeeService;
import model.Employee;
public class Main {
    static void end()
    {
        System.out.println("============================================");
        System.out.println("                 THE END");
        System.out.println("============================================");
        System.out.println("------------------***-----------------------");
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        EmployeeService emp=new EmployeeService();
        boolean service=true;
        while(service)
        {
            System.out.println("============================================");
            System.out.println("         EMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("============================================");
            System.out.println();
            System.out.println("  [1] Add Employee");
            System.out.println("  [2] View All Employees");
            System.out.println("  [3] Update Employee");
            System.out.println("  [4] Delete Employee");
            System.out.println("  [5] Exit");
            System.out.println();
            System.out.println("--------------------------------------------");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice= sc.nextInt();
            }
            catch (InputMismatchException i)
            {
                System.out.println("Enter a valid input");
                sc.nextLine();
                continue;
            }
            switch(choice)
            {
                case 1:
                    System.out.println("============================================");
                    System.out.println("           ADD EMPLOYEE WINDOW");
                    System.out.println("============================================");
                    sc.nextLine();
                    System.out.print("First Name: ");
                    String firstName=sc.nextLine();
                    System.out.print("Last Name: ");
                    String lastName=sc.nextLine();
                    System.out.print("Department: ");
                    String department=sc.nextLine();
                    System.out.print("Salary: ");
                    double salary=sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Email: ");
                    String email=sc.nextLine();
                    Employee newEmployee=new Employee(0,firstName,lastName,department,salary,email);
                    emp.addEmployee(newEmployee);
                    end();
                    break;
                case 2:
                    System.out.println("============================================");
                    System.out.println("         EMPLOYEE LIST");
                    System.out.println("============================================");
                    emp.viewAllEmployees();
                    end();
                    break;
                case 3:
                    System.out.println("============================================");
                    System.out.println("         UPDATE EMPLOYEE WINDOW");
                    System.out.println("============================================");
                    int id = 0;
                    boolean idLoop = true;
                    while (idLoop)
                    {
                        System.out.print("Employee ID: ");
                        try
                        {
                            id = sc.nextInt();
                            sc.nextLine();
                            idLoop = false;
                        }
                        catch (InputMismatchException i)
                        {
                            System.out.println("Invalid input. Please enter a numeric ID.");
                            sc.nextLine();
                        }
                    }
                    boolean updateLoop=true;
                    int updateChoice=0;
                    while(updateLoop)
                    {
                        System.out.println("\nSelect field to update:");
                        System.out.println("[1] Department\n[2] Salary\n[3] Email\n[4] All\n[5] Go Back");
                        System.out.print("Enter your choice: ");

                        try
                        {
                            updateChoice = sc.nextInt();
                            sc.nextLine();
                            if (updateChoice >= 1 && updateChoice <= 5)
                            {
                                updateLoop = false;   // only exit if the number is actually valid
                            }
                            else
                            {
                                System.out.println("Please enter a number between 1 and 5.");
                            }
                        }
                        catch (InputMismatchException i)
                        {
                            System.out.println("Invalid input. Please try again.");
                            sc.nextLine();
                        }
                    }
                    switch(updateChoice)
                    {
                        case 1:
                            System.out.print("New Department: ");
                            String updatedDepartment=sc.nextLine();
                            emp.updateDepartment(id,updatedDepartment);
                            break;
                        case 2:
                            System.out.print("New Salary: ");
                            double updatedSalary=sc.nextDouble();
                            emp.updateSalary(id,updatedSalary);
                            break;
                        case 3:
                            System.out.print("New Email: ");
                            String updatedEmail= sc.nextLine();
                            emp.updateEmail(id,updatedEmail);
                            break;
                        case 4:
                            System.out.print("New Department: ");
                            String newDepartment=sc.nextLine();
                            System.out.print("New Salary: ");
                            double newSalary=sc.nextDouble();
                            sc.nextLine();
                            System.out.print("New Email: ");
                            String newEmail= sc.nextLine();
                            emp.updateAll(id,newDepartment,newSalary,newEmail);
                            break;
                        case 5:
                            break;

                    }
                    end();
                    break;
                case 4:
                    System.out.println("============================================");
                    System.out.println("         DELETE EMPLOYEE WINDOW");
                    System.out.println("============================================");
                    int id1 = 0;
                    boolean idLoop1 = true;
                    while (idLoop1)
                    {
                        System.out.print("Employee ID: ");
                        try
                        {
                            id1 = sc.nextInt();
                            sc.nextLine();
                            idLoop1 = false;
                        }
                        catch (InputMismatchException i)
                        {
                            System.out.println("Invalid input. Please enter a numeric ID.");
                            sc.nextLine();
                        }
                    }
                    emp.deleteEmployee(id1);
                    end();
                    break;
                case 5:
                    System.out.println("============================================");
                    System.out.println("        Thanks for using our service");
                    System.out.println("============================================");
                    service=false;
                    break;
                default:
                    System.out.println("============================================");
                    System.out.println("        !!!Enter a valid option!!!");
                    System.out.println("============================================");
                    System.out.println("------------------***-----------------------");
            }

        }

    }
}