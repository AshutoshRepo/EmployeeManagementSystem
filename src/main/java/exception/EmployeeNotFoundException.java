package exception;

/**
 * Thrown when no employee exists with the given id.
 */
public class EmployeeNotFoundException extends Exception{
    public EmployeeNotFoundException(String message)
    {
        super(message);
    }
}