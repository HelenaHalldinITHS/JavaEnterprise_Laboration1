package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.exception.ConflictException;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.transaction.TransactionalException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Optional;

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
    public Response findAllSubjects() {
        List<Subject> subjects = subjectService.findAllSubjects();
        return Response.ok(subjects).build();
    }

    @Path("{id}")
    @GET
    public Response findById(@PathParam("id") Long id) {
        Optional<Subject> subject = subjectService.findById(id);
        if (subject.isEmpty())
            throw new NotFoundException("A subject with id " + id + " doesn't exist");
        return Response.ok(subject).build();
    }

    @Path("{id}")
    @PUT
    public Response updateSubject(@PathParam("id") Long id, Subject subject) {
        try {
            subjectService.updateSubject(subject, id);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("A subject with id " + subject.getId() + " doesn't exist and therefor can't be updated");
        }
        return Response.ok(subject).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id) {
        try {
            subjectService.deleteSubject(id);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("A subject with id " + id + " doesn't exist and therefor can't be deleted");
        }
        return Response.ok().build();
    }
}
