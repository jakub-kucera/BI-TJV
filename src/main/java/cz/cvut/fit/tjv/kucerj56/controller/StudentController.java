package cz.cvut.fit.tjv.kucerj56.controller;

import cz.cvut.fit.tjv.kucerj56.dto.StudentCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.StudentDTO;
import cz.cvut.fit.tjv.kucerj56.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO create(@RequestBody StudentCreateDTO studentCreateDTO) {
        try {
            return studentService.create(studentCreateDTO);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.toString());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }

    @GetMapping("/giveScholarShip/{amount}")
    public List<StudentDTO> giveScholarShip(@PathVariable String amount) {
        return studentService.giveScholarShip(parseInt(amount));
    }

    @GetMapping("/{id}")
    public StudentDTO readOne(@PathVariable String id) {
        return studentService.findByIdAsDTO(parseInt(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such student with id: " + id));
    }

    @GetMapping
    public List<StudentDTO> readAll() {
        return studentService.findAllDTO();
    }

    @PutMapping("/{id}")
    public StudentDTO update(@PathVariable String id, @RequestBody StudentCreateDTO studentCreateDTO) {
        try {
            return studentService.update(parseInt(id), studentCreateDTO);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        System.out.println("delete id: " + id);
        studentService.delete(parseInt(id));
    }

}
