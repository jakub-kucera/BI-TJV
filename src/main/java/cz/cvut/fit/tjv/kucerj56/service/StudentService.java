package cz.cvut.fit.tjv.kucerj56.service;

import cz.cvut.fit.tjv.kucerj56.dto.StudentDTO;
import cz.cvut.fit.tjv.kucerj56.entity.Faculty;
import cz.cvut.fit.tjv.kucerj56.entity.Student;
import cz.cvut.fit.tjv.kucerj56.entity.Student;
import cz.cvut.fit.tjv.kucerj56.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
                student.getFaculties().stream().map(Faculty::getId).collect(Collectors.toSet())
        );
    }

    private Optional<StudentDTO> toDTO(Optional<Student> optionalDormitory) {
        if (!optionalDormitory.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(optionalDormitory.get()));
    }
}
