package cz.cvut.fit.tjv.kucerj56;

import cz.cvut.fit.tjv.kucerj56.dto.FacultyCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.FacultyDTO;
import cz.cvut.fit.tjv.kucerj56.service.FacultyService;
import cz.cvut.fit.tjv.kucerj56.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FacultyServiceTest {
    private final FacultyService facultyService;
    private final StudentService studentService;

    @Autowired
    public FacultyServiceTest(FacultyService facultyService, StudentService studentService) {
        this.facultyService = facultyService;
        this.studentService = studentService;
    }


//    @Test
    public void findNone() {
        assertEquals(facultyService.findAllDTO().size(), 0);
    }

    @Test
    public void create() {
        List<Integer> emptyList = new ArrayList<>();
        FacultyCreateDTO newFaculty = new FacultyCreateDTO("create", emptyList);
        FacultyDTO createdFaculty = facultyService.create(newFaculty);
        assertTrue(
                newFaculty.getName().equals(createdFaculty.getName()) &&
                        newFaculty.getStudents().equals(createdFaculty.getStudents()));
    }

    @Test
    public void findByAsDTOId() {
        List<Integer> emptyList = new ArrayList<>();
        FacultyCreateDTO newFaculty = new FacultyCreateDTO("findByAsDTOId", emptyList);
        FacultyDTO createdFaculty = facultyService.create(newFaculty);
        Optional<FacultyDTO> foundFacultyDTOOptional = facultyService.findByIdAsDTO(createdFaculty.getId());

        FacultyDTO foundFacultyDTO = foundFacultyDTOOptional.orElseThrow(AssertionError::new);

        assertEquals(foundFacultyDTO, createdFaculty);


    }

    @Test
    public void update() {
        List<Integer> emptyList = new ArrayList<>();
        FacultyCreateDTO newFaculty = new FacultyCreateDTO("update", emptyList);
        FacultyDTO createdFaculty = facultyService.create(newFaculty);

        FacultyCreateDTO newUpdatedFaculty = new FacultyCreateDTO("newUpdate", emptyList);
        FacultyDTO updatedFaculty = facultyService.update(createdFaculty.getId(), newUpdatedFaculty);

        Optional<FacultyDTO> foundUpdatedFacultyDTOOptional = facultyService.findByIdAsDTO(createdFaculty.getId());

        FacultyDTO foundUpdatedFacultyDTO = foundUpdatedFacultyDTOOptional.orElseThrow(AssertionError::new);

        assertEquals(updatedFaculty, foundUpdatedFacultyDTO);

    }

    @Test
    public void findAllDTO() {
        List<Integer> emptyList = new ArrayList<>();
        int oldNumberOfFaculties = facultyService.findAllDTO().size();

        facultyService.create(new FacultyCreateDTO("findAllDTO1", emptyList));
        facultyService.create(new FacultyCreateDTO("findAllDTO2", emptyList));
        facultyService.create(new FacultyCreateDTO("findAllDTO3", emptyList));
        facultyService.create(new FacultyCreateDTO("findAllDTO4", emptyList));
        facultyService.create(new FacultyCreateDTO("findAllDTO5", emptyList));
        facultyService.create(new FacultyCreateDTO("findAllDTO6", emptyList));
        facultyService.create(new FacultyCreateDTO("findAllDTO7", emptyList));

        int newNumberOfFaculties = facultyService.findAllDTO().size();

        assertEquals(oldNumberOfFaculties + 7, newNumberOfFaculties);
    }


    @Test
    public void deleteAll() {
        int oldNumberOfStudents = studentService.findAllDTO().size();
        List<FacultyDTO> allFaculties = facultyService.findAllDTO();
        allFaculties.forEach((x) -> facultyService.delete(x.getId()));
        int newNumberOfStudents = studentService.findAllDTO().size();

        assertTrue(facultyService.findAllDTO().size() == 0 &&
                newNumberOfStudents == oldNumberOfStudents);

        assertEquals(0, facultyService.findAllDTO().size());
    }
}
