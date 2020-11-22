package cz.cvut.fit.tjv.kucerj56.dto;

import com.sun.istack.NotNull;
import cz.cvut.fit.tjv.kucerj56.entity.Faculty;
import cz.cvut.fit.tjv.kucerj56.entity.Room;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

public class StudentDTO {

    private final int id;
    private final String name;
    private final Date dateOfBirth;
    private final Integer roomId;
    private final Double stateOfAccount;
    private final List<Integer> facultyIds;

    public StudentDTO(int id, String name, Date dateOfBirth, Integer roomId, Double stateOfAccount, List<Integer> facultyIds) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.roomId = roomId;
        this.stateOfAccount = stateOfAccount;
        this.facultyIds = facultyIds;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Double getStateOfAccount() {
        return stateOfAccount;
    }

    public List<Integer> getFacultyIds() {
        return facultyIds;
    }
}
