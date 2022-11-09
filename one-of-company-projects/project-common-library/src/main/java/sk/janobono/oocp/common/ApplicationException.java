package sk.janobono.oocp.common;

public class ApplicationException extends RuntimeException {

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
