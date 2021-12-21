package by.lamaka.application.exceptions;

public class ValidateException extends Exception {
    public ValidateException() {
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Exception e) {
        super(message, e);
    }

    public ValidateException(Exception e) {
        super(e);
    }
}
