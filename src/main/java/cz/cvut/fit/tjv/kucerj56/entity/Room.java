package cz.cvut.fit.tjv.kucerj56.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue
//    @Column(name = "ID_ROOM")
    private int id;

    @NotNull
//    @Column(name = "NUMBER_ROOM")
    private int numberRoom;

    @NotNull
//    @Column(name = "QUARANTINE_ROOM")
    private Boolean quarantineRoom;

//    @Column(name = "PRICE_PER_DAY")
    private Double pricePerDay;

//    @Column(name = "NUMBER_OF_BEDS")
    private int numberOfBeds;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dormitory_id")
    private Dormitory dormitory;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)//(targetEntity = Student.class)
    @JoinColumn(name = "room_id")
    private List<Student> students;

    public Room() {
    }

    public Room(int numberRoom, Boolean quarantineRoom, Double pricePerDay, int numberOfBeds, Dormitory dormitory, List<Student> students) {
        this.numberRoom = numberRoom;
        this.quarantineRoom = quarantineRoom;
        this.pricePerDay = pricePerDay;
        this.numberOfBeds = numberOfBeds;
        this.dormitory = dormitory;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public int getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(int numberRoom) {
        this.numberRoom = numberRoom;
    }

    public Boolean getQuarantineRoom() {
        return quarantineRoom;
    }

    public void setQuarantineRoom(Boolean quarantineRoom) {
        this.quarantineRoom = quarantineRoom;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
