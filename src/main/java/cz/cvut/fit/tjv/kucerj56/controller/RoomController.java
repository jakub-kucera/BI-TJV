package cz.cvut.fit.tjv.kucerj56.controller;

import cz.cvut.fit.tjv.kucerj56.dto.RoomCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.RoomDTO;
import cz.cvut.fit.tjv.kucerj56.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping(value = "/api/rooms", produces = "application/vhd.suz-rooms+json", consumes = "application/vhd.suz-rooms+json")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomDTO create(@RequestBody RoomCreateDTO roomCreateDTO) {
        try {
            return roomService.create(roomCreateDTO);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.toString());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }

    @GetMapping("/{id}")
    public RoomDTO readOne(@PathVariable String id) {
        return roomService.findByIdAsDTO(parseInt(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such room with id: " + id));
    }

    @GetMapping
    public List<RoomDTO> readAll() {
        return roomService.findAllDTO();
    }

    @PutMapping("/{id}")
    public RoomDTO update(@PathVariable String id, @RequestBody RoomCreateDTO roomCreateDTO) {
        try {
            return roomService.update(parseInt(id), roomCreateDTO);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        System.out.println("delete id: " + id);
        roomService.delete(parseInt(id));
    }

}
