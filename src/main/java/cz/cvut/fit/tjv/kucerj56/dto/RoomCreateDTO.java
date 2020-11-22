package cz.cvut.fit.tjv.kucerj56.dto;

import java.util.List;

public class RoomCreateDTO {
    private final int numberRoom;
    private final Boolean quarantineRoom;
    private final Double pricePerDay;
    private final int numberOfBeds;
    private final Integer dormitoryId;
    private final List<Integer> studentIds;

    public RoomCreateDTO(int numberRoom, Boolean quarantineRoom, Double pricePerDay, int numberOfBeds, Integer dormitoryId, List<Integer> studentIds) {
        this.numberRoom = numberRoom;
        this.quarantineRoom = quarantineRoom;
        this.pricePerDay = pricePerDay;
        this.numberOfBeds = numberOfBeds;
        this.dormitoryId = dormitoryId;
        this.studentIds = studentIds;
    }
    public int getNumberRoom() {
        return numberRoom;
    }

    public Boolean getQuarantineRoom() {
        return quarantineRoom;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public List<Integer> getStudentIds() {
        return studentIds;
    }
}
