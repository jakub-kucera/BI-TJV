package cz.cvut.fit.tjv.kucerj56.dto;

import java.sql.Date;
import java.util.List;

public class StudentCreateDTO {

    private final String name;
    private final Date dateOfBirth;
    private final Integer roomId;
    private final Double stateOfAccount;
    private final List<Integer> facultyIds;

    public StudentCreateDTO(String name, Date dateOfBirth, Integer roomId, Double stateOfAccount, List<Integer> facultyIds) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.roomId = roomId;
        this.stateOfAccount = stateOfAccount;
        this.facultyIds = facultyIds;
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
