package cz.cvut.fit.tjv.kucerj56.controller;

import cz.cvut.fit.tjv.kucerj56.dto.FacultyCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.FacultyDTO;
import cz.cvut.fit.tjv.kucerj56.service.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping(value = "/api/faculties"/*, produces = "application/vhd.suz-faculties+json", consumes = "application/vhd.suz-faculties+json"*/)
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FacultyDTO create(@RequestBody FacultyCreateDTO facultyCreateDTO) {
        try {
            return facultyService.create(facultyCreateDTO);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.toString());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }

    @GetMapping("/{id}")
    public FacultyDTO readOne(@PathVariable String id) {
        return facultyService.findByIdAsDTO(parseInt(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such faculty with id: " + id));
    }

    @GetMapping
    public List<FacultyDTO> readAll() {
        return facultyService.findAllDTO();
    }

    @PutMapping("/{id}")
    public FacultyDTO update(@PathVariable String id, @RequestBody FacultyCreateDTO facultyCreateDTO) {
        try {
            return facultyService.update(parseInt(id), facultyCreateDTO);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        System.out.println("delete id: " + id);
        facultyService.delete(parseInt(id));
    }

}
