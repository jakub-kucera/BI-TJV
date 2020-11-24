package cz.cvut.fit.tjv.kucerj56.dto;

import com.sun.istack.NotNull;
import cz.cvut.fit.tjv.kucerj56.entity.Room;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

public class DormitoryDTO {
    private final int id;
    private final String name;
    private final List<Integer> roomIds;

    public DormitoryDTO(int id, String name, List<Integer> roomIds) {
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

    public List<Integer> getRoomIds() {
        return roomIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DormitoryDTO that = (DormitoryDTO) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(roomIds, that.roomIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, roomIds);
    }
}
