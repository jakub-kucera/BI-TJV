package cz.cvut.fit.tjv.kucerj56.dto;

import com.sun.istack.NotNull;
import cz.cvut.fit.tjv.kucerj56.entity.Dormitory;
import cz.cvut.fit.tjv.kucerj56.entity.Student;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

public class RoomDTO {
    private final int id;
    private final int numberRoom;
    private final Boolean quarantineRoom;
    private final Double pricePerDay;
    private final int numberOfBeds;
    private final Integer dormitoryId;
    private final List<Integer> studentIds;

    public RoomDTO(int id, int numberRoom, Boolean quarantineRoom, Double pricePerDay, int numberOfBeds, Integer dormitoryId, List<Integer> studentIds) {
        this.id = id;
        this.numberRoom = numberRoom;
        this.quarantineRoom = quarantineRoom;
        this.pricePerDay = pricePerDay;
        this.numberOfBeds = numberOfBeds;
        this.dormitoryId = dormitoryId;
        this.studentIds = studentIds;
    }

    public int getId() {
        return id;
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
        RoomDTO roomDTO = (RoomDTO) o;
        return id == roomDTO.id &&
                numberRoom == roomDTO.numberRoom &&
                numberOfBeds == roomDTO.numberOfBeds &&
                quarantineRoom.equals(roomDTO.quarantineRoom) &&
                pricePerDay.equals(roomDTO.pricePerDay) &&
                dormitoryId.equals(roomDTO.dormitoryId) &&
                studentIds.equals(roomDTO.studentIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberRoom, quarantineRoom, pricePerDay, numberOfBeds, dormitoryId, studentIds);
    }
}
