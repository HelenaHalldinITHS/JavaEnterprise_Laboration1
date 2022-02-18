package se.iths.exception;

import se.iths.message.ErrorMessage;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage()
                .setStatusCode(Response.Status.NOT_FOUND.getStatusCode())
                .setMessage(e.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
    }
}
