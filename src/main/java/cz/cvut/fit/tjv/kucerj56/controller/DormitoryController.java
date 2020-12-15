package cz.cvut.fit.tjv.kucerj56.controller;

import cz.cvut.fit.tjv.kucerj56.dto.DormitoryCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.DormitoryDTO;
import cz.cvut.fit.tjv.kucerj56.entity.Dormitory;
import cz.cvut.fit.tjv.kucerj56.service.DormitoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping(value = "/api/dormitories"/*, produces = "application/vhd.suz-dormitories+json", consumes = "application/vhd.suz-dormitories+json"*/)
public class DormitoryController {
    private final DormitoryService dormitoryService;

    public DormitoryController(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }

//    @PatchMapping
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DormitoryDTO create(@RequestBody DormitoryCreateDTO dormitoryCreateDTO) {
        try {
            return dormitoryService.create(dormitoryCreateDTO);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.toString());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }

    @GetMapping("/{id}")
    public DormitoryDTO readOne(@PathVariable String id) {
        return dormitoryService.findByIdAsDTO(parseInt(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such dormitory with id: " + id));
    }

    @GetMapping
    public List<DormitoryDTO> readAll() {
        return dormitoryService.findAllDTO();
    }

    @PutMapping("/{id}")
    public DormitoryDTO update(@PathVariable String id, @RequestBody DormitoryCreateDTO dormitoryCreateDTO) {
        try{
            return dormitoryService.update(parseInt(id),dormitoryCreateDTO);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        System.out.println("delete id: " + id);
        dormitoryService.delete(parseInt(id));
    }

}
