package cz.cvut.fit.tjv.kucerj56.dto;

import java.util.Set;

public class DormitoryCreateDTO {
    private final String name;
    private final Set<Integer> roomIds;

    public DormitoryCreateDTO(String name, Set<Integer> roomIds) {
        this.name = name;
        this.roomIds = roomIds;
    }

    public String getName() {
        return name;
    }

    public Set<Integer> getRoomIds() {
        return roomIds;
    }
}
