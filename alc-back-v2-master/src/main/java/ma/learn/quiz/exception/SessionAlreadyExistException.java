package ma.learn.quiz.exception;

public class SessionAlreadyExistException extends Exception {
    public SessionAlreadyExistException(String message) {
        super(message);
    }
}
