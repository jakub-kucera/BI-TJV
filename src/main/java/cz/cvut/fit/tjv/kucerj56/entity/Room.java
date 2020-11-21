package cz.cvut.fit.tjv.kucerj56.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue
    private int id_room;

    @NotNull
    private int number_room;

    @NotNull
    private Character quarantine_room;

    private Double price_per_day;

    private int number_of_beds;

    @ManyToOne
    @JoinColumn(name = "DORMATORY_ID_DORMATORY")
    private Dormatory dormatory;

    @OneToMany//(targetEntity = Student.class)
    @JoinColumn(name = "ROOM_ID_ROOM")
    private Set<Student> students;

    public Room() {
    }

    public Room(int number_room, Character quarantine_room, Double price_per_day, int number_of_beds, Dormatory dormatory, Set<Student> students) {
        this.number_room = number_room;
        this.quarantine_room = quarantine_room;
        this.price_per_day = price_per_day;
        this.number_of_beds = number_of_beds;
        this.dormatory = dormatory;
        this.students = students;
    }

    public int getId_room() {
        return id_room;
    }

    public int getNumber_room() {
        return number_room;
    }

    public void setNumber_room(int number_room) {
        this.number_room = number_room;
    }

    public Character getQuarantine_room() {
        return quarantine_room;
    }

    public void setQuarantine_room(Character quarantine_room) {
        this.quarantine_room = quarantine_room;
    }

    public Double getPrice_per_day() {
        return price_per_day;
    }

    public void setPrice_per_day(Double price_per_day) {
        this.price_per_day = price_per_day;
    }

    public int getNumber_of_beds() {
        return number_of_beds;
    }

    public void setNumber_of_beds(int number_of_beds) {
        this.number_of_beds = number_of_beds;
    }

    public Dormatory getDormatory() {
        return dormatory;
    }

    public void setDormatory(Dormatory dormatory) {
        this.dormatory = dormatory;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
