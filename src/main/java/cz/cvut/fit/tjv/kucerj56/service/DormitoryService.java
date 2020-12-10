package cz.cvut.fit.tjv.kucerj56.service;

import cz.cvut.fit.tjv.kucerj56.dto.DormitoryCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.DormitoryDTO;
import cz.cvut.fit.tjv.kucerj56.entity.Dormitory;
import cz.cvut.fit.tjv.kucerj56.entity.Room;
import cz.cvut.fit.tjv.kucerj56.repository.DormitoryRepository;
import cz.cvut.fit.tjv.kucerj56.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DormitoryService {
    private final DormitoryRepository dormitoryRepository;
    private final RoomRepository roomRepository;
    private final RoomService roomService;

    @Autowired
    public DormitoryService(DormitoryRepository dormitoryRepository, RoomService roomService, RoomRepository roomRepository) {
        this.dormitoryRepository = dormitoryRepository;
        this.roomService = roomService;
        this.roomRepository = roomRepository;
    }

    public List<Dormitory> findAll() {
        return dormitoryRepository.findAll();
    }

    public List<DormitoryDTO> findAllDTO() {
        return findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Dormitory> findByIds(List<Integer> ids) {
        return dormitoryRepository.findAllById(ids);
    }

    public List<DormitoryDTO> findByIdsDTO(List<Integer> ids) {
        return findByIds(ids).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<Dormitory> findById(int id) {
        return dormitoryRepository.findById(id);
    }

    public Optional<DormitoryDTO> findByIdAsDTO(int id) {
        return toDTO(findById(id));
    }

    private DormitoryDTO toDTO(Dormitory dormitory) {
        return new DormitoryDTO(dormitory.getId(), dormitory.getName(), dormitory.getRooms().stream().map(Room::getId).collect(Collectors.toList()));
    }

    private Optional<DormitoryDTO> toDTO(Optional<Dormitory> optionalDormitory) {
        if (!optionalDormitory.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(toDTO(optionalDormitory.get()));
    }

    @Transactional
    public DormitoryDTO create(DormitoryCreateDTO dormitoryCreateDTO) {
        if(dormitoryRepository.findByName(dormitoryCreateDTO.getName()).isPresent()) {
            throw new EntityExistsException("Dormitory already exists: " + dormitoryCreateDTO.getName());
        }

        List<Room> rooms = roomRepository.findAllById(dormitoryCreateDTO.getRoomIds());
        int roomsMissing = dormitoryCreateDTO.getRoomIds().size() - rooms.size();
        if (roomsMissing != 0) {
            throw new NoSuchElementException("Number of rooms missing is: " + roomsMissing);
        }

        Dormitory dormitory = new Dormitory(dormitoryCreateDTO.getName(), rooms);
        return toDTO(dormitoryRepository.save(dormitory));
    }

    @Transactional
    public DormitoryDTO update(int id, DormitoryCreateDTO dormitoryCreateDTO) {
        Optional<Dormitory> optionalDormitory = findById(id);
        if (!optionalDormitory.isPresent()) {
            throw new NoSuchElementException("No such dormitory with id: " + id);
        }
        Dormitory dormitory = optionalDormitory.get();

        List<Room> rooms = roomRepository.findAllById(dormitoryCreateDTO.getRoomIds());
        int roomsMissing = dormitoryCreateDTO.getRoomIds().size() - rooms.size();
        if (roomsMissing != 0) {
            throw new NoSuchElementException("Number of rooms missing is: " + roomsMissing);
        }

        dormitory.setName(dormitoryCreateDTO.getName());
        dormitory.setRooms(rooms);

        return toDTO(dormitory);
    }

    @Transactional
    public void delete(int id){
        Optional<Dormitory> optionalDormitory = findById(id);

        List<Integer> roomIds = toDTO(optionalDormitory
                .orElseThrow(() -> new NoSuchElementException("No such dormitory with id: " + id)))
                .getRoomIds();

        for (Integer roomId : roomIds) {
            roomService.delete(roomId);
        }
        System.out.println("deleting dormitory named: " + optionalDormitory.get().getName() + " ID: " + optionalDormitory.get().getId());
        dormitoryRepository.deleteById(id);
    }
}
