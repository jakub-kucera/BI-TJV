package cz.cvut.fit.tjv.kucerj56.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DormitoryCreateDTO /*implements MySerializableClass */{
    private final String name;
    private final List<Integer> roomIds;

    public DormitoryCreateDTO() {
        name = "";
        roomIds = new ArrayList<>();
    }

    public DormitoryCreateDTO(String name, List<Integer> roomIds) {
        this.name = name;
        this.roomIds = roomIds;
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
        DormitoryCreateDTO that = (DormitoryCreateDTO) o;
        return name.equals(that.name) &&
                roomIds.equals(that.roomIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, roomIds);
    }
}
