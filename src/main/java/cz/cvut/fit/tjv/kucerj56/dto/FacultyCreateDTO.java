package cz.cvut.fit.tjv.kucerj56.dto;

import java.util.List;

public class FacultyCreateDTO {

    private final String name;
//    private final List<Integer> studentIds;

    public FacultyCreateDTO(String name/*, List<Integer> studentIds*/) {
        this.name = name;
//        this.studentIds = studentIds;
    }


    public String getName() {
        return name;
    }
//    public List<Integer> getStudents() {
//        return studentIds;
//    }
}
