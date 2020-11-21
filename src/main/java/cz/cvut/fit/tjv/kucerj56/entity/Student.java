package cz.cvut.fit.tjv.kucerj56.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "ID_STUDENT")
    private int id;

    @NotNull
    private String name;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID_ROOM")
    private Room room;

    @Column(name = "STATE_OF_ACCOUNT")
    private Double stateOfAccount;

    @ManyToMany
    @JoinTable(name = "STUDY", joinColumns = @JoinColumn(name = "STUDENT_ID_STUDENT"), inverseJoinColumns = @JoinColumn(name = "FACULTY_ID_FACULTY"))
    private Set<Faculty> faculties;

    public Student() {
    }

    public Student(String name, Date dateOfBirth, Room room, Double stateOfAccount, Set<Faculty> faculties) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.room = room;
        this.stateOfAccount = stateOfAccount;
        this.faculties = faculties;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Double getStateOfAccount() {
        return stateOfAccount;
    }

    public void setStateOfAccount(Double stateOfAccount) {
        this.stateOfAccount = stateOfAccount;
    }

    public Set<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(Set<Faculty> faculties) {
        this.faculties = faculties;
    }
}
