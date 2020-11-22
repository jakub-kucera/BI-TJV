package cz.cvut.fit.tjv.kucerj56.service;

import cz.cvut.fit.tjv.kucerj56.dto.StudentCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.StudentDTO;
import cz.cvut.fit.tjv.kucerj56.entity.*;
import cz.cvut.fit.tjv.kucerj56.entity.Student;
import cz.cvut.fit.tjv.kucerj56.repository.FacultyRepository;
import cz.cvut.fit.tjv.kucerj56.repository.RoomRepository;
import cz.cvut.fit.tjv.kucerj56.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
//    private final FacultyService facultyService;
    private final FacultyRepository facultyRepository;
//    private final RoomService roomService;
    private final RoomRepository roomRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository,/* FacultyService facultyService, */FacultyRepository facultyRepository,/* RoomService roomService, */RoomRepository roomRepository) {
        this.studentRepository = studentRepository;
//        this.facultyService = facultyService;
        this.facultyRepository = facultyRepository;
        this.roomRepository = roomRepository;
//        this.roomService = roomService;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<StudentDTO> findAllDTO() {
        return findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Student> findByIds(List<Integer> ids) {
        return studentRepository.findAllById(ids);
    }

    public List<StudentDTO> findByIdsDTO(List<Integer> ids) {
        return findByIds(ids).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<Student> findById(int id) {
        return studentRepository.findById(id);
    }

    public Optional<StudentDTO> findByIdAsDTO(int id) {
        return toDTO(findById(id));
    }

    private StudentDTO toDTO(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getDateOfBirth(),
                student.getRoom().getId(),
                student.getStateOfAccount(),
                student.getFaculties().stream().map(Faculty::getId).collect(Collectors.toList())
        );
    }

    private Optional<StudentDTO> toDTO(Optional<Student> optionalDormitory) {
        if (!optionalDormitory.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(optionalDormitory.get()));
    }

    public StudentDTO create (StudentCreateDTO studentCreateDTO) throws Exception {
//        List<Faculty> faculties = facultyService.findByIds(studentCreateDTO.getFacultyIds());
        List<Faculty> faculties = facultyRepository.findAllById(studentCreateDTO.getFacultyIds());
        int facultiesMissing = studentCreateDTO.getFacultyIds().size() - faculties.size();
        if(facultiesMissing != 0) {
            throw new Exception("Number of faculties missing is: " + facultiesMissing);
        }

        Integer roomId = studentCreateDTO.getRoomId();
//        Room room = roomId == null ? null : roomService.findById(roomId).orElseThrow(() -> new Exception("No such room with id: " + roomId));
        Room room = roomId == null ? null : roomRepository.findById(roomId).orElseThrow(() -> new Exception("No such room with id: " + roomId));

        Student student = new Student(
                studentCreateDTO.getName(),
                studentCreateDTO.getDateOfBirth(),
                room,
                studentCreateDTO.getStateOfAccount(),
                faculties);
        return toDTO(studentRepository.save(student));
    }

    @Transactional
    public StudentDTO update (int id, StudentCreateDTO studentCreateDTO) throws Exception {
        Optional<Student> optionalStudent = findById(id);
        if(!optionalStudent.isPresent()) {
            throw new Exception("No such student with id: " + id);
        }
        Student student = optionalStudent.get();

//        List<Faculty> faculties = facultyService.findByIds(studentCreateDTO.getFacultyIds());
        List<Faculty> faculties = facultyRepository.findAllById(studentCreateDTO.getFacultyIds());
        int facultiesMissing = studentCreateDTO.getFacultyIds().size() - faculties.size();
        if(facultiesMissing != 0) {
            throw new Exception("Number of faculties missing is: " + facultiesMissing);
        }

        Integer roomId = studentCreateDTO.getRoomId();
//        Room room = roomId == null ? null : roomService.findById(roomId).orElseThrow(() -> new Exception("No such room with id: " + roomId));
        Room room = roomId == null ? null : roomRepository.findById(roomId).orElseThrow(() -> new Exception("No such room with id: " + roomId));

        student.setName(studentCreateDTO.getName());
        student.setDateOfBirth(studentCreateDTO.getDateOfBirth());
        student.setRoom(room);
        student.setStateOfAccount(studentCreateDTO.getStateOfAccount());
        student.setFaculties(faculties);

        return toDTO(student);

    }
}
