package cz.cvut.fit.tjv.kucerj56.service;

import cz.cvut.fit.tjv.kucerj56.dto.FacultyCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.FacultyDTO;
import cz.cvut.fit.tjv.kucerj56.entity.Faculty;
import cz.cvut.fit.tjv.kucerj56.entity.Student;
import cz.cvut.fit.tjv.kucerj56.repository.FacultyRepository;
import cz.cvut.fit.tjv.kucerj56.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
//    private final StudentService studentService;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository, /*StudentService studentService, */StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
//        this.studentService = studentService;
    }

    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }

    public List<FacultyDTO> findAllDTO() {
        return findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Faculty> findByIds(List<Integer> ids) {
        return facultyRepository.findAllById(ids);
    }

    public List<FacultyDTO> findByIdsDTO(List<Integer> ids) {
        return findByIds(ids).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<Faculty> findById(int id) {
        return facultyRepository.findById(id);
    }

    public Optional<FacultyDTO> findByIdAsDTO(int id) {
        return toDTO(findById(id));
    }

    private FacultyDTO toDTO(Faculty faculty) {
        return new FacultyDTO(faculty.getId(), faculty.getName(), faculty.getStudents().stream().map(Student::getId).collect(Collectors.toList()));
    }

    private Optional<FacultyDTO> toDTO(Optional<Faculty> optionalFaculty) {
        if (!optionalFaculty.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(optionalFaculty.get()));
    }

    public FacultyDTO create(FacultyCreateDTO facultyCreateDTO) {
        if(facultyRepository.findByName(facultyCreateDTO.getName()).isPresent()) {
            throw new EntityExistsException("Faculty already exists: " + facultyCreateDTO.getName());
        }

        List<Student> students = studentRepository.findAllById(facultyCreateDTO.getStudents());
        int studentsMissing = facultyCreateDTO.getStudents().size() - students.size();
        if (studentsMissing != 0) {
            throw new NoSuchElementException("Number of students missing is: " + studentsMissing);
        }

        return toDTO(facultyRepository.save(new Faculty(facultyCreateDTO.getName(), students)));
    }

    @Transactional
    public FacultyDTO update(int id, FacultyCreateDTO facultyCreateDTO) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        Faculty faculty = optionalFaculty.orElseThrow(() ->  new NoSuchElementException("No such faculty with id: " + id));

        List<Student> students = studentRepository.findAllById(facultyCreateDTO.getStudents());
        int studentsMissing = facultyCreateDTO.getStudents().size() - students.size();
        if (studentsMissing != 0) {
            throw new NoSuchElementException("Number of students missing is: " + studentsMissing);
        }

        faculty.setName(facultyCreateDTO.getName());
        faculty.setStudents(students);
        return toDTO(faculty);
    }

    @Transactional
    public void delete(int id) {
        if (!findById(id).isPresent()) {
            throw new NoSuchElementException("No such faculty with id: " + id);
        }
        facultyRepository.deleteById(id);
    }

}
