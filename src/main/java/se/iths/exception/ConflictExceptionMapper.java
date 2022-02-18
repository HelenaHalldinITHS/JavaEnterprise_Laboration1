package se.iths.exception;

import se.iths.message.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConflictExceptionMapper implements ExceptionMapper<ConflictException> {

    @Override
    public Response toResponse(ConflictException e) {
        ErrorMessage errorMessage = new ErrorMessage()
                .setStatusCode(Response.Status.CONFLICT.getStatusCode())
                .setMessage(e.getMessage());
        return Response.status(Response.Status.CONFLICT).entity(errorMessage).build();
    }
}
