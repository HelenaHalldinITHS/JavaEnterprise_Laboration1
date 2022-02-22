package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.exception.ConflictException;
import se.iths.service.TeacherService;

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

@Path("teachers")
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Path("")
    @GET
    public Response findAllTeachers(){
        List<Teacher> teachers = teacherService.findAllTeachers();
        return Response.ok(teachers).build();
    }

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher, @Context UriInfo uriInfo) {
        try {
            teacherService.createTeacher(teacher);
        } catch (TransactionalException e) {
            throw new ConflictException("A student with id " + teacher.getId() + " already exist and therefor can't be added");
        }
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(Long.toString(teacher.getId()));
        return Response.created(uriBuilder.build()).build();
    }
}
