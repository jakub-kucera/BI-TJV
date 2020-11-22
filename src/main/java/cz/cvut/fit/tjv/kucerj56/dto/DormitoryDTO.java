package cz.cvut.fit.tjv.kucerj56.dto;

import com.sun.istack.NotNull;
import cz.cvut.fit.tjv.kucerj56.entity.Room;

import javax.persistence.*;
import java.util.Set;

public class DormitoryDTO {
    private final int id;
    private final String name;
    private final Set<Integer> roomIds;

    public DormitoryDTO(int id, String name, Set<Integer> roomIds) {
        this.id = id;
        this.name = name;
        this.roomIds = roomIds;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Integer> getRoomIds() {
        return roomIds;
    }
}
