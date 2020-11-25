package cz.cvut.fit.tjv.kucerj56;

import cz.cvut.fit.tjv.kucerj56.dto.DormitoryCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.DormitoryDTO;
import cz.cvut.fit.tjv.kucerj56.dto.RoomCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.RoomDTO;
import cz.cvut.fit.tjv.kucerj56.service.DormitoryService;
import cz.cvut.fit.tjv.kucerj56.service.RoomService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoomServiceTest {
    private final RoomService roomService;
    private final DormitoryService dormitoryService;

    @Autowired
    public RoomServiceTest(RoomService roomService, DormitoryService dormitoryService) {
        this.roomService = roomService;
        this.dormitoryService = dormitoryService;
    }


//    @Test
//    @Order(1)//todo maybe dont use
    public void findNone() {
        assertEquals(roomService.findAllDTO().size(), 0);
    }

    @Test
//    @Order(2)
    public void create() {
        List<Integer> emptyStudentList = new ArrayList<>();
        DormitoryDTO createdDormitory = dormitoryService
                .create(new DormitoryCreateDTO("dormitoryForRoomTestingCreate", new ArrayList<>()));
        RoomCreateDTO newRoom = new RoomCreateDTO(
                225, false, 110.0, 2, createdDormitory.getId(), emptyStudentList);
        RoomDTO createdRoom = roomService.create(newRoom);

        assertTrue(createdRoom.getNumberRoom() == newRoom.getNumberRoom() &&
                createdRoom.getNumberOfBeds() == newRoom.getNumberOfBeds() &&
                createdRoom.getQuarantineRoom().equals(newRoom.getQuarantineRoom()) &&
                createdRoom.getPricePerDay().equals(newRoom.getPricePerDay()) &&
                createdRoom.getDormitoryId().equals(newRoom.getDormitoryId()) &&
                createdRoom.getStudentIds().equals(newRoom.getStudentIds()));
    }

    @Test
//    @Order(3)
    public void findByAsDTOId() {

        List<Integer> emptyStudentList = new ArrayList<>();
        DormitoryDTO createdDormitory = dormitoryService
                .create(new DormitoryCreateDTO("dormitoryForRoomTestingFindByAsDTOId", new ArrayList<>()));
        RoomCreateDTO newRoom = new RoomCreateDTO(
                3434, false, 150.0, 1, createdDormitory.getId(), emptyStudentList);
        RoomDTO createdRoom = roomService.create(newRoom);

        Optional<RoomDTO> foundRoomDTOOptional = roomService.findByIdAsDTO(createdRoom.getId());

        RoomDTO foundRoomDTO = foundRoomDTOOptional.orElseThrow(AssertionError::new);

        assertEquals(foundRoomDTO, createdRoom);


    }

    @Test
//    @Order(4)
    public void update() {

        List<Integer> emptyStudentList = new ArrayList<>();
        DormitoryDTO createdDormitory = dormitoryService
                .create(new DormitoryCreateDTO("dormitoryForRoomTestingUpdate", new ArrayList<>()));
        RoomCreateDTO newRoom = new RoomCreateDTO(
                123, false, 2.0, 20, createdDormitory.getId(), emptyStudentList);
        RoomDTO createdRoom = roomService.create(newRoom);

        RoomCreateDTO newUpdatedRoom = new RoomCreateDTO(
                134323, false, 232.5, 1, createdDormitory.getId(), emptyStudentList);
        RoomDTO updatedRoom = roomService.update(createdRoom.getId(), newUpdatedRoom);

        Optional<RoomDTO> foundUpdatedRoomOptional = roomService.findByIdAsDTO(createdRoom.getId());

        RoomDTO foundUpdatedRoomDTO = foundUpdatedRoomOptional.orElseThrow(AssertionError::new);

        assertEquals(updatedRoom, foundUpdatedRoomDTO);

    }

    @Test
//    @Order(5)
    public void findAllDTO() {
        List<Integer> emptyList = new ArrayList<>();
        int oldNumberOfRooms = roomService.findAllDTO().size();

        DormitoryDTO createdDormitory1 = dormitoryService.create(new DormitoryCreateDTO("dormitoryForRoomTestingfindAllDTO1", emptyList));
        DormitoryDTO createdDormitory2 = dormitoryService.create(new DormitoryCreateDTO("dormitoryForRoomTestingfindAllDTO2", emptyList));

        roomService.create(new RoomCreateDTO(
                1, true, 1.1, 10, createdDormitory1.getId(), emptyList));
        roomService.create(new RoomCreateDTO(
                2, true, 2.2, 20, createdDormitory2.getId(), emptyList));
        roomService.create(new RoomCreateDTO(
                3, true, 3.3, 30, createdDormitory1.getId(), emptyList));
        roomService.create(new RoomCreateDTO(
                4, true, 4.4, 40, createdDormitory2.getId(), emptyList));
        roomService.create(new RoomCreateDTO(
                5, true, 5.5, 50, createdDormitory1.getId(), emptyList));
        roomService.create(new RoomCreateDTO(
                6, true, 6.6, 60, createdDormitory2.getId(), emptyList));
        roomService.create(new RoomCreateDTO(
                7, true, 7.7, 70, createdDormitory1.getId(), emptyList));


        int newNumberOfRooms = roomService.findAllDTO().size();

        assertEquals(oldNumberOfRooms + 7, newNumberOfRooms);
    }


    @Test
//    @Order(100)
    public void deleteAll() {
        List<RoomDTO> allRooms = roomService.findAllDTO();
        int oldNumberOfDormitories = dormitoryService.findAllDTO().size();
        allRooms.forEach((x) -> {
//            System.out.println(x.getNumberRoom());
            roomService.delete(x.getId());});
        int newNumberOfDormitories = dormitoryService.findAllDTO().size();

        assertTrue(0 == roomService.findAllDTO().size() &&
                newNumberOfDormitories == oldNumberOfDormitories);

    }
}
