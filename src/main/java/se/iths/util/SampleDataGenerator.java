package se.iths.util;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData() {
        //Skapa några lärare
        Teacher teacher1 = new Teacher("Eddie", "Neuman");
        Teacher teacher2 = new Teacher("Martin", "Blomberg");
        //Skapa några kurser
        Subject subject1 = new Subject("Java Enterprice", teacher1);
        Subject subject2 = new Subject("Databaser", teacher2);
        Subject subject3 = new Subject("Byggverktyg");

        //Skapa några studenter
        Student student1 = new Student("Helena", "Halldin", "helena@example.se", "012345678");
        Student student2 = new Student("Kajsa", "Svensson", "kajsa@example.se", "012345678");
        Student student3 = new Student("Tobias", "Eklund", "tobias@example.se", "012345678");

        //Lägg till lärare för kurser
        teacher2.addSubject(subject3);

        //Lägg till förhållande mellan studenter och kurser
        student1.addSubject(subject1);
        student1.addSubject(subject2);
        student1.addSubject(subject3);
        student2.addSubject(subject2);


        //persist
        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.persist(subject1);
        entityManager.persist(subject2);
        entityManager.persist(subject3);
        entityManager.persist(teacher1);
        entityManager.persist(teacher2);
    }

}
