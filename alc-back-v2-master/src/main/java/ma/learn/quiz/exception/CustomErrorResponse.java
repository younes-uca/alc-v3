package ma.learn.quiz.exception;

public class CustomErrorResponse {
    private String error;
    private String message;
    // constructor and getters and setters


    public CustomErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
