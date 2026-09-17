import java.util.InputMismatchException;
import java.util.Scanner;
import service.EmployeeService;
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
                    System.out.println("Add employee");
                    end();
                    break;
                case 2:
                    System.out.println("============================================");
                    System.out.println("         EMPLOYEE LIST");
                    System.out.println("============================================");
                    System.out.println("View All Employees");
                    end();
                    break;
                case 3:
                    System.out.println("============================================");
                    System.out.println("         UPDATE EMPLOYEE WINDOW");
                    System.out.println("============================================");
                    System.out.println("Update Employee");
                    end();
                    break;
                case 4:
                    System.out.println("============================================");
                    System.out.println("         DELETE EMPLOYEE WINDOW");
                    System.out.println("============================================");
                    System.out.println("Delete Employee");
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