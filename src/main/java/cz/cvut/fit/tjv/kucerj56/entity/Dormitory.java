package cz.cvut.fit.tjv.kucerj56.entity;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.List;


@Entity()
public class Dormitory {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "DORMITORY_ID")
    private List<Room> rooms;

    public Dormitory() {
    }

    public Dormitory(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
