package cz.cvut.fit.tjv.kucerj56.dto;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCreateDTO that = (StudentCreateDTO) o;
        return name.equals(that.name) &&
                dateOfBirth.equals(that.dateOfBirth) &&
                roomId.equals(that.roomId) &&
                stateOfAccount.equals(that.stateOfAccount) &&
                facultyIds.equals(that.facultyIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, roomId, stateOfAccount, facultyIds);
    }
}
