package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


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

    public void updateStudent(Student student) {
        Student foundStudent = findStudentById(student.getId());
        entityManager.merge(foundStudent);
    }

    public void deleteStudent(Long id) {
        Student student = findStudentById(id);
        entityManager.remove(student);
    }

    public List<Student> findStudentsByLastName(String lastName) {
        return entityManager.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName", Student.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

}
