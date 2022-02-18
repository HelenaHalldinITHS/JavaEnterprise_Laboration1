package se.iths.exception;

import javax.ws.rs.WebApplicationException;

public class ConflictException extends WebApplicationException {
    public ConflictException(String message) {
        super(message);
    }
}
