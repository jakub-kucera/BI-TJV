package cz.cvut.fit.tjv.kucerj56.dto;

import java.util.Set;

public class RoomCreateDTO {
    private final int numberRoom;
    private final Character quarantineRoom;
    private final Double pricePerDay;
    private final int numberOfBeds;
    private final Integer dormitoryId;
    private final Set<Integer> studentIds;

    public RoomCreateDTO(int numberRoom, Character quarantineRoom, Double pricePerDay, int numberOfBeds, Integer dormitoryId, Set<Integer> studentIds) {
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

    public Character getQuarantineRoom() {
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

    public Set<Integer> getStudentIds() {
        return studentIds;
    }
}
