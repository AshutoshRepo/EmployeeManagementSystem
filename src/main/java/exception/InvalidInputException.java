package exception;

/**
 * Thrown when user input is not valid, for example a blank field,
 * a badly formatted email, or a salary or id that is 0 or negative.
 */
public class InvalidInputException extends Exception{
    public InvalidInputException(String message)
    {
        super(message);
    }
}