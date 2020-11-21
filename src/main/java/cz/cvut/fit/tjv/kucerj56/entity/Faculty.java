package cz.cvut.fit.tjv.kucerj56.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Faculty {
    @Id
    @GeneratedValue
    private int id_faculty;

    @NotNull
    private String name;

    @ManyToMany
    @JoinTable(name = "STUDY", joinColumns = @JoinColumn(name = "FACULTY_ID_FACULTY"), inverseJoinColumns = @JoinColumn(name = "STUDENT_ID_STUDENT"))
    private Set<Student> students;

    public Faculty() {}


    public Faculty(String name, Set<Student> students) {
        this.name = name;
        this.students = students;
    }

    public int getId_faculty() {
        return id_faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
