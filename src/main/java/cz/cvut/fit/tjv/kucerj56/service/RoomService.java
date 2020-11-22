package cz.cvut.fit.tjv.kucerj56.service;

import cz.cvut.fit.tjv.kucerj56.dto.RoomDTO;
import cz.cvut.fit.tjv.kucerj56.entity.Dormitory;
import cz.cvut.fit.tjv.kucerj56.entity.Room;
import cz.cvut.fit.tjv.kucerj56.entity.Room;
import cz.cvut.fit.tjv.kucerj56.entity.Student;
import cz.cvut.fit.tjv.kucerj56.repository.RoomRepository;
import cz.cvut.fit.tjv.kucerj56.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public List<RoomDTO> findAllDTO() {
        return findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Room> findByIds(List<Integer> ids) {
        return roomRepository.findAllById(ids);
    }

    public List<RoomDTO> findByIdsDTO(List<Integer> ids) {
        return findByIds(ids).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<Room> findById(int id) {
        return roomRepository.findById(id);
    }

    public Optional<RoomDTO> findByIdAsDTO(int id) {
        return toDTO(findById(id));
    }

    private RoomDTO toDTO(Room room) {
        return new RoomDTO(
                room.getId(),
                room.getNumberRoom(),
                room.getQuarantineRoom(),
                room.getPricePerDay(),
                room.getNumberOfBeds(),
                room.getDormitory().getId(),
                room.getStudents().stream().map(Student::getId).collect(Collectors.toSet()));
    }

    private Optional<RoomDTO> toDTO(Optional<Room> optionalDormitory) {
        if (!optionalDormitory.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(optionalDormitory.get()));
    }
}
