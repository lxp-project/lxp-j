package jdbc.course.exception;

public class UserCancelException extends RuntimeException {
    public UserCancelException(String message) {
        super(message);
    }
}
