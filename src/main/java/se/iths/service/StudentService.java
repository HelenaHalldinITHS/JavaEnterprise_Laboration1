package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;


@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void createStudent(Student student) {
            entityManager.persist(student);
    }

    public Student findStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> findAllStudents() {
        return entityManager.createQuery("SELECT s from Student s", Student.class).getResultList();
    }

    public void updateStudent(Student student){
        Optional<Student> optionalStudent = Optional.ofNullable(findStudentById(student.getId()));
        entityManager.merge(optionalStudent.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND)));
    }

    public void deleteStudent(Long id) {
        Student student = findStudentById(id);
        entityManager.remove(student);
    }

    public List<Student> findStudentsByLastName(String lastName) {
        TypedQuery<Student> query = entityManager
                .createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName", Student.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

}
