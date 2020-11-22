package cz.cvut.fit.tjv.kucerj56.dto;

import java.util.Set;

public class FacultyCreateDTO {

    private final String name;
    private final Set<Integer> studentIds;

    public FacultyCreateDTO(String name, Set<Integer> studentIds) {
        this.name = name;
        this.studentIds = studentIds;
    }


    public String getName() {
        return name;
    }
    public Set<Integer> getStudents() {
        return studentIds;
    }
}
