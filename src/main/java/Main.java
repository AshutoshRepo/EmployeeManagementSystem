import java.util.InputMismatchException;
import java.util.Scanner;
import service.EmployeeService;
public class Main {

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
                int tempChoice=sc.nextInt();
                choice=tempChoice;
            }
            catch (InputMismatchException i)
            {
                System.out.println("Enter a valid input");
                sc.nextLine();
                continue;
            }
            if(choice==5)
            {
                System.out.println("============================================");
                System.out.println("        Thanks for using our service");
                System.out.println("============================================");
                service=false;
            }

        }

    }
}