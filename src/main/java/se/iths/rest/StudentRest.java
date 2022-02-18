package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.transaction.TransactionalException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @POST
    public Response createStudent(Student student) {
        try {
            studentService.createStudent(student);
            return Response.created(URI.create("http://localhost:8080/student-management-system/api/v1/students/" + student.getId())).build();
        } catch (TransactionalException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("{id}")
    @GET
    public Response findStudentById(@PathParam("id") Long id) {
        Student student = studentService.findStudentById(id);
        return Response.ok(student).build();
    }

    @GET
    public Response findAllStudents() {
        List<Student> students = studentService.findAllStudents();
        return Response.ok(students).build();
    }


    @PUT
    public Response updateStudent(Student student) {
        studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.deleteStudent(id);
        return Response.ok().build();
    }

    @Path("get")
    @GET
    public Response findStudentByLastName(@QueryParam("lastName") String lastName) {
        List<Student> students = studentService.findStudentsByLastName(lastName);
        return Response.ok(students).build();
    }

}
