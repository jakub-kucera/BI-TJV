package cz.cvut.fit.tjv.kucerj56.service;

import cz.cvut.fit.tjv.kucerj56.dto.RoomCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.RoomDTO;
import cz.cvut.fit.tjv.kucerj56.entity.Dormitory;
import cz.cvut.fit.tjv.kucerj56.entity.Room;
import cz.cvut.fit.tjv.kucerj56.entity.Student;
import cz.cvut.fit.tjv.kucerj56.repository.DormitoryRepository;
import cz.cvut.fit.tjv.kucerj56.repository.RoomRepository;
import cz.cvut.fit.tjv.kucerj56.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;
    private final DormitoryRepository dormitoryRepository;
    private final StudentService studentService;
//    private final DormitoryService dormitoryService;

    @Autowired
    public RoomService(RoomRepository roomRepository, StudentService studentService, /*, DormitoryService dormitoryService*/StudentRepository studentRepository, DormitoryRepository dormitoryRepository) {
        this.roomRepository = roomRepository;
        this.studentService = studentService;
//        this.dormitoryService = dormitoryService;
        this.studentRepository = studentRepository;
        this.dormitoryRepository = dormitoryRepository;
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
                room.getStudents().stream().map(Student::getId).collect(Collectors.toList()));
    }

    private Optional<RoomDTO> toDTO(Optional<Room> optionalDormitory) {
        if (!optionalDormitory.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(optionalDormitory.get()));
    }

    public RoomDTO create(RoomCreateDTO roomCreateDTO) {
        List<Student> students = studentRepository.findAllById(roomCreateDTO.getStudentIds());
        int studentsMissing = roomCreateDTO.getStudentIds().size() - students.size();
        if (studentsMissing != 0) {
            throw new NoSuchElementException("Number of students missing is: " + studentsMissing);
        }

        Integer dormitoryId = roomCreateDTO.getDormitoryId();
        Dormitory dormitory = dormitoryId == null ? null :
                dormitoryRepository.findById(dormitoryId)
                .orElseThrow(() -> new NoSuchElementException("No such dormitory with id: " + dormitoryId));

        Room room = new Room(
                roomCreateDTO.getNumberRoom(),
                roomCreateDTO.getQuarantineRoom(),
                roomCreateDTO.getPricePerDay(),
                roomCreateDTO.getNumberOfBeds(),
                dormitory,
                students);
        return toDTO(roomRepository.save(room));
    }

    @Transactional
    public RoomDTO update(int id, RoomCreateDTO roomCreateDTO) {
        Optional<Room> optionalRoom = findById(id);
        Room room = optionalRoom.orElseThrow(() -> new NoSuchElementException("No such room with id: " + id));

        List<Student> students = studentRepository.findAllById(roomCreateDTO.getStudentIds());
        int studentsMissing = roomCreateDTO.getStudentIds().size() - students.size();
        if (studentsMissing != 0) {
            throw new NoSuchElementException("Number of students missing is: " + studentsMissing);
        }

        Integer dormitoryId = roomCreateDTO.getDormitoryId();
        Dormitory dormitory = dormitoryId == null ? null : dormitoryRepository.findById(dormitoryId).orElseThrow(() -> new NoSuchElementException("No such dormitory with id: " + dormitoryId));

        room.setNumberRoom(roomCreateDTO.getNumberRoom());
        room.setQuarantineRoom(roomCreateDTO.getQuarantineRoom());
        room.setPricePerDay(roomCreateDTO.getPricePerDay());
        room.setNumberOfBeds(roomCreateDTO.getNumberOfBeds());
        room.setDormitory(dormitory);
        room.setStudents(students);

        return toDTO(room);
    }

    @Transactional
    public void delete(int id) {
        Optional<Room> optionalRoom = findById(id);

        List<Integer> studentIds = toDTO(optionalRoom
                .orElseThrow(() -> new NoSuchElementException("No such room with id: " + id)))
                .getStudentIds();

        for (Integer studentId : studentIds) {
            studentService.delete(studentId);
        }
//        System.out.println("deleting room named: " + optionalRoom.get().getNumberRoom() + " ID: " + optionalRoom.get().getId());
        roomRepository.deleteById(id);
    }
}
