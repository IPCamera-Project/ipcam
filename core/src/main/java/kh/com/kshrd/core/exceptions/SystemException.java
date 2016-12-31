package kh.com.kshrd.core.exceptions;

/**
 * Created by sophatvathana on 20/12/16.
 */
public class SystemException extends Exception {

    private static final long serialVersionUID = 1L;

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}

