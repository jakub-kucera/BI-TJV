package cz.cvut.fit.tjv.kucerj56.dto;

import java.sql.Date;
import java.util.Set;

public class StudentCreateDTO {

    private final String name;
    private final Date dateOfBirth;
    private final Integer roomId;
    private final Double stateOfAccount;
    private final Set<Integer> facultyIds;

    public StudentCreateDTO(String name, Date dateOfBirth, Integer roomId, Double stateOfAccount, Set<Integer> facultyIds) {
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

    public Set<Integer> getFacultyIds() {
        return facultyIds;
    }
}
