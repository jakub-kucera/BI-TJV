package cz.cvut.fit.tjv.kucerj56.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomCreateDTO {
    private final int numberRoom;
    private final Boolean quarantineRoom;
    private final Double pricePerDay;
    private final int numberOfBeds;
    private final Integer dormitoryId;
    private final List<Integer> studentIds;

//    public RoomCreateDTO() {
//        numberRoom = 0;
//        quarantineRoom = false;
//        pricePerDay = 0.0;
//        numberOfBeds = 0;
//        dormitoryId = 0;
//        studentIds = new ArrayList<>();
//    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomCreateDTO that = (RoomCreateDTO) o;
        return numberRoom == that.numberRoom &&
                numberOfBeds == that.numberOfBeds &&
                quarantineRoom.equals(that.quarantineRoom) &&
                pricePerDay.equals(that.pricePerDay) &&
                dormitoryId.equals(that.dormitoryId) &&
                studentIds.equals(that.studentIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberRoom, quarantineRoom, pricePerDay, numberOfBeds, dormitoryId, studentIds);
    }
}
