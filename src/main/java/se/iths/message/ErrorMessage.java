package se.iths.message;

import java.sql.Timestamp;
import java.time.Instant;

public class ErrorMessage {
    private String message;
    private int status;
    private String error;
    private Timestamp timestamp = Timestamp.from(Instant.now());

    public String getError() {
        return error;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public ErrorMessage setError(String error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public ErrorMessage setStatus(int status) {
        this.status = status;
        return this;
    }

}
