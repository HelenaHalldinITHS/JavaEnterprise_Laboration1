package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //I do not want to remove a subject if it has no teacher (for the moment)
    private Set<Subject> subjects = new HashSet<>();

    public Teacher(String firstName, String lastName, Set<Subject> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subjects = subjects;
    }

    public Teacher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Teacher() {}

    public void addSubject(Subject subject){
        subjects.add(subject);
        subject.setTeacher(this);
    }

    public void removeSubject(Subject subject){
        subjects.remove(subject);
        subject.setTeacher(null);
    }

    @JsonbTransient
    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
