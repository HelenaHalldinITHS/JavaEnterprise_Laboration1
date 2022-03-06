package se.iths.service;

import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public void createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
    }

    public List<Teacher> findAllTeachers() {
        return entityManager.createQuery("SELECT t from Teacher t", Teacher.class).getResultList();
    }

    public Optional<Teacher> findTeacherById(Long id) {
        return Optional.ofNullable(entityManager.find(Teacher.class, id));
    }

    public void deleteTeacher(Long id) {
        Optional<Teacher> teacher = findTeacherById(id);
        entityManager.remove(teacher.orElseThrow(IllegalArgumentException::new));
    }

    public void updateTeacher(Teacher teacher, Long id) {
        if (findTeacherById(id).isEmpty())
            throw new IllegalArgumentException();
        teacher.setId(id);
        entityManager.merge(teacher);
    }
}
