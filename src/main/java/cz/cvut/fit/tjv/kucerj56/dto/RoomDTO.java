package cz.cvut.fit.tjv.kucerj56.dto;

import com.sun.istack.NotNull;
import cz.cvut.fit.tjv.kucerj56.entity.Dormitory;
import cz.cvut.fit.tjv.kucerj56.entity.Student;

import javax.persistence.*;
import java.util.List;

public class RoomDTO {
    private final int id;
    private final int numberRoom;
    private final Boolean quarantineRoom;
    private final Double pricePerDay;
    private final int numberOfBeds;
    private final Integer dormitoryId;
//    private final List<Integer> studentIds;

    public RoomDTO(int id, int numberRoom, Boolean quarantineRoom, Double pricePerDay, int numberOfBeds, Integer dormitoryId/*, List<Integer> studentIds*/) {
        this.id = id;
        this.numberRoom = numberRoom;
        this.quarantineRoom = quarantineRoom;
        this.pricePerDay = pricePerDay;
        this.numberOfBeds = numberOfBeds;
        this.dormitoryId = dormitoryId;
//        this.studentIds = studentIds;
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

//    public List<Integer> getStudentIds() {
//        return studentIds;
//    }
}
