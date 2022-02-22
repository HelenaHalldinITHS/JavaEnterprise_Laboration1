package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.exception.ConflictException;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.transaction.TransactionalException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("subjects")
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Path("")
    @POST
    public Response createSubject(Subject subject, @Context UriInfo uriInfo) {
        try {
            subjectService.createSubject(subject);
        } catch (TransactionalException e) {
            throw new ConflictException("A student with id " + subject.getId() + " already exist and therefor can't be added");
        }
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(Long.toString(subject.getId()));
        return Response.created(uriBuilder.build()).build();
    }

    @Path("")
    @GET
    public Response findAllSubjects(){
        List<Subject> subjects = subjectService.findAllSubjects();
        return Response.ok(subjects).build();
    }

}
