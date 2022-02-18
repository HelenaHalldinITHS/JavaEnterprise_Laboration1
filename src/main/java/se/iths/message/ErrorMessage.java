package se.iths.message;

public class ErrorMessage {
    private String message;
    private int statusCode;

    public ErrorMessage(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ErrorMessage(){}

    public String getMessage() {
        return message;
    }

    public ErrorMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public ErrorMessage setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }
}
