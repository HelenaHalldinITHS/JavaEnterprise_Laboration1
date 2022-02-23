package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.exception.ConflictException;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.transaction.TransactionalException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Optional;

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
            throw new ConflictException("A Teacher with id " + teacher.getId() + " already exist and therefor can't be added");
        }
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(Long.toString(teacher.getId()));
        return Response.created(uriBuilder.build()).build();
    }

    @Path("{id}")
    @GET
    public Response findTeacherByID(@PathParam("id") Long id){
        Optional<Teacher> teacher = teacherService.findTeacherById(id);
        if (teacher.isEmpty())
            throw new NotFoundException("A teacher with id " + id + " doesn't exist");
       return Response.ok(teacher).build();
    }


    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id){
        try{
            teacherService.deleteTeacher(id);
        } catch (IllegalArgumentException e){
            throw new NotFoundException("A teacher with id " + id + " doesn't exist and therefor can't be deleted");
        }
        return Response.ok().build();
    }
}
