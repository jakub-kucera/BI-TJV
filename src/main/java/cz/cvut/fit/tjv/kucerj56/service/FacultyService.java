package cz.cvut.fit.tjv.kucerj56.service;

import cz.cvut.fit.tjv.kucerj56.dto.FacultyCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.FacultyDTO;
import cz.cvut.fit.tjv.kucerj56.entity.Faculty;
import cz.cvut.fit.tjv.kucerj56.entity.Student;
import cz.cvut.fit.tjv.kucerj56.repository.FacultyRepository;
import cz.cvut.fit.tjv.kucerj56.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
//    private final StudentService studentService;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository/*, StudentService studentService*/) {
        this.facultyRepository = facultyRepository;
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
        return  toDTO(findById(id));
    }

    private FacultyDTO toDTO(Faculty faculty) {
        return new FacultyDTO(faculty.getId(), faculty.getName()/*, faculty.getStudents().stream().map(Student::getId).collect(Collectors.toList())*/);
    }

    private Optional<FacultyDTO> toDTO(Optional<Faculty> optionalFaculty) {
        if(!optionalFaculty.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(optionalFaculty.get()));
    }

    public FacultyDTO create(FacultyCreateDTO facultyCreateDTO) throws Exception {
//        List<Student> students = studentService.findByIds(facultyCreateDTO.getStudents());
//        int studentsMissing = facultyCreateDTO.getStudents().size() - students.size();
//        if(studentsMissing == 0) {
//            throw new Exception("Number of students missing is: " + studentsMissing);
//        }

        return toDTO(facultyRepository.save( new Faculty(facultyCreateDTO.getName()/*,students*/)));
    }

    @Transactional
    public FacultyDTO update(int id, FacultyCreateDTO facultyCreateDTO) throws Exception {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (!optionalFaculty.isPresent()) {
            throw new Exception("No such faculty with id: " + id);
        }
        Faculty faculty = optionalFaculty.get();
//
//        List<Student> students = studentService.findByIds(facultyCreateDTO.getStudents());
//        int studentsMissing = facultyCreateDTO.getStudents().size() - students.size();
//        if(studentsMissing == 0) {
//            throw new Exception("Number of students missing is: " + studentsMissing);
//        }

        faculty.setName(facultyCreateDTO.getName());
//        faculty.setStudents(students);
        return toDTO(faculty);
    }

}
