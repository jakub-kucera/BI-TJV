package cz.cvut.fit.tjv.kucerj56.dto;

import com.sun.istack.NotNull;
import cz.cvut.fit.tjv.kucerj56.entity.Student;

import javax.persistence.*;
import java.util.Set;

public class FacultyDTO {

    private final int id;
    private final String name;
    private final Set<Integer> studentIds;

    public FacultyDTO(int id, String name, Set<Integer> studentIDs) {
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
    public Set<Integer> getStudents() {
        return studentIds;
    }
}
