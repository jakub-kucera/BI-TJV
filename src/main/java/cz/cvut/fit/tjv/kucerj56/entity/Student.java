package cz.cvut.fit.tjv.kucerj56.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;//todo fix format

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    private Double stateOfAccount;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "study", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    private List<Faculty> faculties;

    public Student() {
    }

    public Student(String name, Date dateOfBirth, Room room, Double stateOfAccount, List<Faculty> faculties) {
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

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }
}
