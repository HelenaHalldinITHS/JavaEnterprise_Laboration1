package se.iths.service;

import se.iths.entity.Subject;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @Inject
    EntityManager entityManager;

    public void createSubject(Subject subject){
        entityManager.persist(subject);
    }

    public List<Subject> findAllSubjects(){
        return entityManager.createQuery("SELECT s from Subject s", Subject.class).getResultList();
    }

}
