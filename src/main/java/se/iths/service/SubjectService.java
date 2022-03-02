package se.iths.service;

import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public void createSubject(Subject subject) {
        entityManager.persist(subject);
    }

    public List<Subject> findAllSubjects() {
        return entityManager.createQuery("SELECT s from Subject s", Subject.class).getResultList();
    }

    public Optional<Subject> findById(Long id){
        return Optional.ofNullable(entityManager.find(Subject.class, id));
    }

    public void updateSubject(Subject subject, Long id) {
        if (findById(id).isEmpty())
            throw new IllegalArgumentException();
        subject.setId(id);
        entityManager.merge(subject);

    }

    public void deleteSubject(Long id) {
        Optional<Subject> subject = findById(id);
        entityManager.remove(subject.orElseThrow(IllegalArgumentException::new));
    }
}
