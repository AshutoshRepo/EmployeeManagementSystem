package exception;

/**
 * Thrown when an email is already registered to another employee.
 */
public class DuplicateEmailException extends Exception{
    public DuplicateEmailException(String message)
    {
        super(message);
    }
}