package cz.cvut.fit.tjv.kucerj56.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private int id_student;

    @NotNull
    private String name;

    private Date date_of_birth;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID_ROOM")
    private Room room;

    private Double state_of_account;

    @ManyToMany
    @JoinTable(name = "STUDY", joinColumns = @JoinColumn(name = "STUDENT_ID_STUDENT"), inverseJoinColumns = @JoinColumn(name = "FACULTY_ID_FACULTY"))
    private Set<Faculty> faculties;

    public Student() {
    }

    public Student(String name, Date date_of_birth, Room room, Double state_of_account, Set<Faculty> faculties) {
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.room = room;
        this.state_of_account = state_of_account;
        this.faculties = faculties;
    }

    public int getId_student() {
        return id_student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_Of_Birth) {
        this.date_of_birth = date_Of_Birth;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room_Id_Room) {
        this.room = room_Id_Room;
    }

    public Double getState_of_account() {
        return state_of_account;
    }

    public void setState_of_account(Double state_Of_Account) {
        this.state_of_account = state_Of_Account;
    }

    public Set<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(Set<Faculty> students) {
        this.faculties = students;
    }
}
