package cz.cvut.fit.tjv.kucerj56.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Dormatory {
    @Id
    @GeneratedValue
    private int id_dormatory;

    @NotNull
    private String name;

    @OneToMany//(targetEntity = Student.class)
    @JoinColumn(name = "DORMATORY_ID_DORMATORY")
    private Set<Room> rooms;

    public Dormatory() {
    }

    public Dormatory(String name, Set<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public int getId_dormatory() {
        return id_dormatory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
