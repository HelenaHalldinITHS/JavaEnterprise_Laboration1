package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exception.ConflictException;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.transaction.TransactionalException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @POST
    public Response createStudent(Student student, @Context UriInfo uriInfo) {
        try {
            studentService.createStudent(student);
        } catch (TransactionalException e) {
            throw new ConflictException("A student with id " + student.getId() + " already exist and therefor can't be added");
        }
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(Long.toString(student.getId()));
        return Response.created(uriBuilder.build()).build();
    }

    @Path("{id}")
    @GET
    public Response findStudentById(@PathParam("id") Long id) {

        Student student = studentService.findStudentById(id);
        if (student == null)
            throw new NotFoundException("A student with id " + id + " doesn't exist");
        return Response.ok(student).build();
    }

    @GET
    public Response findAllStudents() {
        List<Student> students = studentService.findAllStudents();
        return Response.ok(students).build();
    }


    @PUT
    public Response updateStudent(Student student) {
        try {
            studentService.updateStudent(student);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("A student with id " + student.getId() + " doesn't exist and therefor can't be updated");
        }

        return Response.ok(student).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        try {
            studentService.deleteStudent(id);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("A student with id " + id + " doesn't exist and therefor can't be deleted");
        }
        return Response.ok().build();
    }

    @Path("get")
    @GET
    public Response findStudentByLastName(@QueryParam("lastName") String lastName) {
        List<Student> students = studentService.findStudentsByLastName(lastName);
        return Response.ok(students).build();
    }

}
