package cz.cvut.fit.tjv.kucerj56.service;

import cz.cvut.fit.tjv.kucerj56.dto.DormitoryDTO;
import cz.cvut.fit.tjv.kucerj56.dto.FacultyDTO;
import cz.cvut.fit.tjv.kucerj56.entity.Dormitory;
import cz.cvut.fit.tjv.kucerj56.entity.Room;
import cz.cvut.fit.tjv.kucerj56.repository.DormitoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DormitoryService {
    private final DormitoryRepository dormitoryRepository;

    @Autowired
    public DormitoryService(DormitoryRepository dormitoryRepository) {
        this.dormitoryRepository = dormitoryRepository;
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
        return new DormitoryDTO(dormitory.getId(), dormitory.getName(), dormitory.getRooms().stream().map(Room::getId).collect(Collectors.toSet()));
    }

    private Optional<DormitoryDTO> toDTO(Optional<Dormitory> optionalDormitory) {
        if(!optionalDormitory.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(toDTO(optionalDormitory.get()));
    }
}
