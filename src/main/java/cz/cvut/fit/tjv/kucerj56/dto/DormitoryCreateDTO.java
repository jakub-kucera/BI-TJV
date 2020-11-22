package cz.cvut.fit.tjv.kucerj56.dto;

import java.util.List;

public class DormitoryCreateDTO {
    private final String name;
//    private final List<Integer> roomIds;

    public DormitoryCreateDTO(String name/*, List<Integer> roomIds*/) {
        this.name = name;
//        this.roomIds = roomIds;
    }

    public String getName() {
        return name;
    }

//    public List<Integer> getRoomIds() {
//        return roomIds;
//    }
}
