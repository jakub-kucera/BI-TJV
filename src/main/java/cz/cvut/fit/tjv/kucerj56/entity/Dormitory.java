package cz.cvut.fit.tjv.kucerj56.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity()
public class Dormitory {
    @Id
    @GeneratedValue
//    @Column(name = "ID_DORMATORY")
    private int id;

    @NotNull
//    @Column(name = "NAME")
    private String name;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST)//(targetEntity = Student.class)
    @JoinColumn(name = "dormitory_id")
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
