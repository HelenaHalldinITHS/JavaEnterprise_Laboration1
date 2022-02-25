package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void createStudent(Student student) {
        entityManager.persist(student);
    }

    public Optional<Student> findStudentById(Long id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    public List<Student> findAllStudents() {
        return entityManager.createQuery("SELECT s from Student s", Student.class).getResultList();
    }

    public void updateStudent(Student student, Long id) {
        if (findStudentById(id).isEmpty())
            throw new IllegalArgumentException();
        student.setId(id);
        entityManager.merge(student);
    }

    public void deleteStudent(Long id) {
        Optional<Student> student = findStudentById(id);
        entityManager.remove(student.orElseThrow(IllegalArgumentException::new));
    }

    public List<Student> findStudentsByLastName(String lastName) {
        return entityManager.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName", Student.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

}
