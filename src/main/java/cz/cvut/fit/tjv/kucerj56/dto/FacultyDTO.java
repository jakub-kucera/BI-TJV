package cz.cvut.fit.tjv.kucerj56.dto;

import com.sun.istack.NotNull;
import cz.cvut.fit.tjv.kucerj56.entity.Student;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

public class FacultyDTO {

    private final int id;
    private final String name;
    private final List<Integer> studentIds;

    public FacultyDTO(int id, String name, List<Integer> studentIDs) {
        this.id = id;
        this.name = name;
        this.studentIds = studentIDs;
    }


    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Integer> getStudents() {
        return studentIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyDTO that = (FacultyDTO) o;
        return id == that.id &&
                name.equals(that.name) &&
                studentIds.equals(that.studentIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, studentIds);
    }
}
