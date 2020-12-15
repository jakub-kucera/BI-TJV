package cz.cvut.fit.tjv.kucerj56.service;

import cz.cvut.fit.tjv.kucerj56.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Date;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StudentServiceTest {
    private final StudentService studentService;
    private final RoomService roomService;
    private final DormitoryService dormitoryService;
    private final FacultyService facultyService;

    @Autowired
    public StudentServiceTest(RoomService roomService, DormitoryService dormitoryService, StudentService studentService, FacultyService facultyService) {
        this.studentService = studentService;
        this.roomService = roomService;
        this.dormitoryService = dormitoryService;
        this.facultyService = facultyService;
    }

//    @Test
    public void findNone() {
        assertEquals(studentService.findAllDTO().size(), 0);
    }

    @Test
    public void create() {
        List<Integer> emptyList = new ArrayList<>();
        DormitoryDTO createdDormitory = dormitoryService
                .create(new DormitoryCreateDTO("dormitoryForStudentsTestingCreate", new ArrayList<>()));
        RoomDTO createdRoom = roomService
                .create(new RoomCreateDTO(
                        50, false, 200.0, 2, createdDormitory.getId(), emptyList));


        StudentCreateDTO newStudent = new StudentCreateDTO(
                "studentForTestingCreate", new Date(System.currentTimeMillis()), createdRoom.getId(), 10000.0, emptyList);
        StudentDTO createdStudent = studentService.create(newStudent);

        assertTrue(createdStudent.getName().equals(newStudent.getName()) &&
                createdStudent.getDateOfBirth().equals( newStudent.getDateOfBirth()) &&
                createdStudent.getRoomId().equals(newStudent.getRoomId()) &&
                createdStudent.getStateOfAccount().equals(newStudent.getStateOfAccount()) &&
                createdStudent.getFacultyIds().equals(newStudent.getFacultyIds()));
    }

    @Test
    public void findByAsDTOId() {
        List<Integer> emptyList = new ArrayList<>();
        DormitoryDTO createdDormitory = dormitoryService
                .create(new DormitoryCreateDTO("dormitoryForStudentsTestingFindByAsDTOId", new ArrayList<>()));
        RoomDTO createdRoom = roomService
                .create(new RoomCreateDTO(
                        100, false, 220.0, 5, createdDormitory.getId(), emptyList));


        StudentCreateDTO newStudent = new StudentCreateDTO(
                "studentForTestingFindByAsDTOId", new Date(System.currentTimeMillis()), createdRoom.getId(), 5000.0, emptyList);
        StudentDTO createdStudent = studentService.create(newStudent);


        Optional<StudentDTO> foundStudentDTOOptional = studentService.findByIdAsDTO(createdStudent.getId());

        StudentDTO foundStudentDTO = foundStudentDTOOptional.orElseThrow(AssertionError::new);

        assertEquals(foundStudentDTO, createdStudent);


    }

    @Test
    public void update() {


        List<Integer> emptyList = new ArrayList<>();
        DormitoryDTO createdDormitory = dormitoryService
                .create(new DormitoryCreateDTO("dormitoryForStudentsTestingUpdate", new ArrayList<>()));
        RoomDTO createdRoom = roomService
                .create(new RoomCreateDTO(
                        102, false, 220.0, 5, createdDormitory.getId(), emptyList));


        StudentCreateDTO newStudent = new StudentCreateDTO(
                "studentForTestingFindByAsDTOId", new Date(System.currentTimeMillis()), createdRoom.getId(), 5000.0, emptyList);
        StudentDTO createdStudent = studentService.create(newStudent);


        StudentCreateDTO newUpdatedStudent = new StudentCreateDTO(
                "updatedStudentForTestingFindByAsDTOId", new Date(System.currentTimeMillis()), createdRoom.getId(), 5010.0, emptyList);
        StudentDTO updatedStudent = studentService.update(createdStudent.getId(), newUpdatedStudent);

        Optional<StudentDTO> foundUpdatedStudentOptional = studentService.findByIdAsDTO(createdStudent.getId());

        StudentDTO foundUpdatedStudentDTO = foundUpdatedStudentOptional.orElseThrow(AssertionError::new);

        assertEquals(updatedStudent, foundUpdatedStudentDTO);

    }

    @Test
    public void findAllDTO() {
        List<Integer> emptyList = new ArrayList<>();
        int oldNumberOfStudents = studentService.findAllDTO().size();

        DormitoryDTO createdDormitory1 = dormitoryService.create(new DormitoryCreateDTO("dormitoryForRoomTestingFindAllDTO1", emptyList));
        DormitoryDTO createdDormitory2 = dormitoryService.create(new DormitoryCreateDTO("dormitoryForRoomTestingFindAllDTO2", emptyList));

        RoomDTO createdRoom1 = roomService.create(new RoomCreateDTO(
                1, true, 1000.0, 10, createdDormitory1.getId(), emptyList));
        RoomDTO createdRoom2 = roomService.create(new RoomCreateDTO(
                2, true, 200.0, 20, createdDormitory2.getId(), emptyList));

        studentService.create(new StudentCreateDTO(
                "studentForTestingFindAllDTO1", new Date(System.currentTimeMillis()), createdRoom1.getId(), 10000.0, emptyList));
        studentService.create(new StudentCreateDTO(
                "studentForTestingFindAllDTO1", new Date(System.currentTimeMillis()), createdRoom2.getId(), 10000.0, emptyList));
        studentService.create(new StudentCreateDTO(
                "studentForTestingFindAllDTO1", new Date(System.currentTimeMillis()), createdRoom1.getId(), 5000.0, emptyList));
        studentService.create(new StudentCreateDTO(
                "studentForTestingFindAllDTO1", new Date(System.currentTimeMillis()), createdRoom2.getId(), 5000.0, emptyList));
        studentService.create(new StudentCreateDTO(
                "studentForTestingFindAllDTO1", new Date(System.currentTimeMillis()), createdRoom1.getId(), 3000.0, emptyList));
        studentService.create(new StudentCreateDTO(
                "studentForTestingFindAllDTO1", new Date(System.currentTimeMillis()), createdRoom2.getId(), 3000.0, emptyList));
        studentService.create(new StudentCreateDTO(
                "studentForTestingFindAllDTO1", new Date(System.currentTimeMillis()), createdRoom1.getId(), 1000.0, emptyList));

        int newNumberOfStudents = studentService.findAllDTO().size();

        assertEquals(oldNumberOfStudents + 7, newNumberOfStudents);
    }


    @Test
    public void deleteAll() {
        List<StudentDTO> allStudents = studentService.findAllDTO();
        int oldNumberOfRooms = roomService.findAllDTO().size();
        int oldNumberOfDormitories = dormitoryService.findAllDTO().size();
        allStudents.forEach((x) -> {
            studentService.delete(x.getId());});
        int newNumberOfRooms = roomService.findAllDTO().size();
        int newNumberOfDormitories = dormitoryService.findAllDTO().size();

        assertTrue(0 == studentService.findAllDTO().size() &&
                newNumberOfRooms == oldNumberOfRooms &&
                newNumberOfDormitories == oldNumberOfDormitories);

    }

    @Test
    public void chargeFeeAll() {
        List<Integer> emptyList = new ArrayList<>();

        DormitoryDTO createdDormitory1 = dormitoryService.create(new DormitoryCreateDTO("dormitoryForRoomTestingChargeFeeAll1", emptyList));
        DormitoryDTO createdDormitory2 = dormitoryService.create(new DormitoryCreateDTO("dormitoryForRoomTestingChargeFeeAll2", emptyList));

        RoomDTO createdRoom1 = roomService.create(new RoomCreateDTO(
                1, true, 1000.0, 10, createdDormitory1.getId(), emptyList));
        RoomDTO createdRoom2 = roomService.create(new RoomCreateDTO(
                2, true, 200.0, 20, createdDormitory2.getId(), emptyList));

        StudentDTO createdStudent1 = studentService.create(new StudentCreateDTO(
                "studentForTestingChargeFeeAll1", new Date(System.currentTimeMillis()), createdRoom1.getId(), 10000.0, emptyList));
        StudentDTO createdStudent2 = studentService.create(new StudentCreateDTO(
                "studentForTestingChargeFeeAll2", new Date(System.currentTimeMillis()), createdRoom2.getId(), 10000.0, emptyList));
        StudentDTO createdStudent3 = studentService.create(new StudentCreateDTO(
                "studentForTestingChargeFeeAll3", new Date(System.currentTimeMillis()), createdRoom1.getId(), 5000.0, emptyList));
        StudentDTO createdStudent4 = studentService.create(new StudentCreateDTO(
                "studentForTestingChargeFeeAll4", new Date(System.currentTimeMillis()), createdRoom2.getId(), 5000.0, emptyList));
        StudentDTO createdStudent5 = studentService.create(new StudentCreateDTO(
                "studentForTestingChargeFeeAll5", new Date(System.currentTimeMillis()), createdRoom1.getId(), 3000.0, emptyList));
        StudentDTO createdStudent6 = studentService.create(new StudentCreateDTO(
                "studentForTestingChargeFeeAll6", new Date(System.currentTimeMillis()), createdRoom2.getId(), 3000.0, emptyList));
        StudentDTO createdStudent7 = studentService.create(new StudentCreateDTO(
                "studentForTestingChargeFeeAll7", new Date(System.currentTimeMillis()), createdRoom1.getId(), 100.0, emptyList));

        List<StudentDTO> studentsInDebt = studentService.chargeFeeAll();


        assertTrue(
                studentService.findByIdAsDTO(createdStudent1.getId()).orElseThrow(
                                () -> new NoSuchElementException("studentForTestingChargeFeeAll1 no found"))
                                .getStateOfAccount() == 9000.0 &&
                        studentService.findByIdAsDTO(createdStudent2.getId()).orElseThrow(
                                () -> new NoSuchElementException("studentForTestingChargeFeeAll2 no found"))
                                .getStateOfAccount() == 9800.0 &&
                        studentService.findByIdAsDTO(createdStudent3.getId()).orElseThrow(
                                () -> new NoSuchElementException("studentForTestingChargeFeeAll3 no found"))
                                .getStateOfAccount() == 4000.0 &&
                        studentService.findByIdAsDTO(createdStudent4.getId()).orElseThrow(
                                () -> new NoSuchElementException("studentForTestingChargeFeeAll4 no found"))
                                .getStateOfAccount() == 4800.0 &&
                        studentService.findByIdAsDTO(createdStudent5.getId()).orElseThrow(
                                () -> new NoSuchElementException("studentForTestingChargeFeeAll5 no found"))
                                .getStateOfAccount() == 2000.0 &&
                        studentService.findByIdAsDTO(createdStudent6.getId()).orElseThrow(
                                () -> new NoSuchElementException("studentForTestingChargeFeeAll6 no found"))
                                .getStateOfAccount() == 2800.0 &&
                        studentService.findByIdAsDTO(createdStudent7.getId()).orElseThrow(
                                () -> new NoSuchElementException("studentForTestingChargeFeeAll7 no found"))
                                .getStateOfAccount() == -900.0 &&
                        studentsInDebt.contains(createdStudent7)
        );
    }

    @Test
    public void giveScholarShip() {
        List<Integer> emptyList = new ArrayList<>();

        DormitoryDTO createdDormitory1 = dormitoryService.create(new DormitoryCreateDTO("dormitoryForRoomTestingGiveScholarShip1", emptyList));
        DormitoryDTO createdDormitory2 = dormitoryService.create(new DormitoryCreateDTO("dormitoryForRoomTestingGiveScholarShip2", emptyList));

        RoomDTO createdRoom1 = roomService.create(new RoomCreateDTO(
                1, true, 1000.0, 10, createdDormitory1.getId(), emptyList));
        RoomDTO createdRoom2 = roomService.create(new RoomCreateDTO(
                2, true, 200.0, 20, createdDormitory2.getId(), emptyList));

        FacultyDTO createdFaculty1 = facultyService.create(new FacultyCreateDTO("giveScholarShip1", emptyList));
        FacultyDTO createdFaculty2 = facultyService.create(new FacultyCreateDTO("giveScholarShip2", emptyList));

        int createdFaculty1Id = createdFaculty1.getId();
        int createdFaculty2Id = createdFaculty2.getId();

        List<Integer> facultyList1 = List.of(createdFaculty1Id);
        List<Integer> facultyList2 = List.of(createdFaculty2Id);
        List<Integer> facultyListBoth = List.of(createdFaculty1Id, createdFaculty2Id);

        StudentDTO createdStudent1 = studentService.create(new StudentCreateDTO(
                "studentForTestingGiveScholarShip1", new Date(System.currentTimeMillis()), createdRoom1.getId(), 10000.0, facultyList1));
        StudentDTO createdStudent2 = studentService.create(new StudentCreateDTO(
                "studentForTestingGiveScholarShip2", new Date(System.currentTimeMillis()), createdRoom2.getId(), 10000.0, facultyList2));
        StudentDTO createdStudent3 = studentService.create(new StudentCreateDTO(
                "studentForTestingGiveScholarShip3", new Date(System.currentTimeMillis()), createdRoom1.getId(), 10000.0, facultyListBoth));
        StudentDTO createdStudent4 = studentService.create(new StudentCreateDTO(
                "studentForTestingGiveScholarShip4", new Date(System.currentTimeMillis()), createdRoom2.getId(), 5000.0, facultyList1));
        StudentDTO createdStudent5 = studentService.create(new StudentCreateDTO(
                "studentForTestingGiveScholarShip5", new Date(System.currentTimeMillis()), createdRoom1.getId(), 5000.0, facultyList2));
        StudentDTO createdStudent6 = studentService.create(new StudentCreateDTO(
                "studentForTestingGiveScholarShip6", new Date(System.currentTimeMillis()), createdRoom2.getId(), 5000.0, facultyListBoth));
        StudentDTO createdStudent7 = studentService.create(new StudentCreateDTO(
                "studentForTestingGiveScholarShip7", new Date(System.currentTimeMillis()), createdRoom1.getId(), 1000.0, facultyList1));


        List<StudentDTO> awardedStudents = studentService.giveScholarShip(90);

        assertTrue(awardedStudents.size() == 2 &&
                studentService.findByIdAsDTO(createdStudent1.getId()).orElseThrow(
                        () -> new NoSuchElementException("studentForTestingGiveScholarShip1 no found"))
                        .getStateOfAccount() == 10000.0 &&
                studentService.findByIdAsDTO(createdStudent2.getId()).orElseThrow(
                        () -> new NoSuchElementException("studentForTestingGiveScholarShip2 no found"))
                        .getStateOfAccount() == 10000.0 &&
                studentService.findByIdAsDTO(createdStudent3.getId()).orElseThrow(
                        () -> new NoSuchElementException("studentForTestingGiveScholarShip3 no found"))
                        .getStateOfAccount() == 10090.0 &&
                studentService.findByIdAsDTO(createdStudent4.getId()).orElseThrow(
                        () -> new NoSuchElementException("studentForTestingGiveScholarShip4 no found"))
                        .getStateOfAccount() == 5000.0 &&
                studentService.findByIdAsDTO(createdStudent5.getId()).orElseThrow(
                        () -> new NoSuchElementException("studentForTestingGiveScholarShip6 no found"))
                        .getStateOfAccount() == 5000.0 &&
                studentService.findByIdAsDTO(createdStudent6.getId()).orElseThrow(
                        () -> new NoSuchElementException("studentForTestingGiveScholarShip7 no found"))
                        .getStateOfAccount() == 5090.0 &&
                studentService.findByIdAsDTO(createdStudent7.getId()).orElseThrow(
                        () -> new NoSuchElementException("studentForTestingGiveScholarShip7 no found"))
                        .getStateOfAccount() == 1000.0

        );

    }
}
