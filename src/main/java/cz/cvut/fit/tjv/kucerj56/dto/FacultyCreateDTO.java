package cz.cvut.fit.tjv.kucerj56.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FacultyCreateDTO {

    private final String name;
    private final List<Integer> studentIds;

    public FacultyCreateDTO() {
        name = "";
        studentIds = new ArrayList<>();
    }

    public FacultyCreateDTO(String name, List<Integer> studentIds) {
        this.name = name;
        this.studentIds = studentIds;
    }


    public String getName() {
        return name;
    }
    public List<Integer> getStudentIds() {
        return studentIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyCreateDTO that = (FacultyCreateDTO) o;
        return name.equals(that.name) &&
                studentIds.equals(that.studentIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, studentIds);
    }
}
