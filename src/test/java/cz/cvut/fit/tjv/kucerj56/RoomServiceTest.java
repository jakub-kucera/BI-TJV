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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoomServiceTest {
    private final RoomService roomService;
//    private final DormitoryService dormitoryService;

    @Autowired
    public RoomServiceTest(RoomService roomService, DormitoryService dormitoryService) {
        this.roomService = roomService;
        this.dormitoryService = dormitoryService;
    }


    @Test
    @Order(1)//todo maybe dont use
    public void findNone() {
        assertEquals(roomService.findAllDTO().size(), 0);
    }

    @Test
    @Order(2)
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
    @Order(3)
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
    @Order(4)
    public void update() {

        List<Integer> emptyStudentList = new ArrayList<>();
        DormitoryDTO createdDormitory = dormitoryService
                .create(new DormitoryCreateDTO("dormitoryForRoomTestingUpdate", new ArrayList<>()));
        RoomCreateDTO newRoom = new RoomCreateDTO(
                123, false, 2.0, 20, createdDormitory.getId(), emptyStudentList);

        List<Integer> emptyRoomList = new ArrayList<>();
        DormitoryCreateDTO newDormitory = new DormitoryCreateDTO("oldUpdate", emptyRoomList);
        DormitoryDTO createdDormitory = dormitoryService.create(newDormitory);

        DormitoryCreateDTO newUpdatedDormitory = new DormitoryCreateDTO("newUpdate", emptyRoomList);
        DormitoryDTO updatedDormitory = dormitoryService.update(createdDormitory.getId(), newUpdatedDormitory);

        Optional<DormitoryDTO> foundUpdatedDormitoryDTOOptional = dormitoryService.findByIdAsDTO(createdDormitory.getId());

        DormitoryDTO foundUpdatedDormitoryDTO = foundUpdatedDormitoryDTOOptional.orElseThrow(AssertionError::new);

        assertEquals(updatedDormitory, foundUpdatedDormitoryDTO);

    }

    public void findAllDTO() {
        List<Integer> emptyRoomList = new ArrayList<>();
        int oldNumberOfDormitories = dormitoryService.findAllDTO().size();

        dormitoryService.create(new DormitoryCreateDTO("findAllDTO1", emptyRoomList));
        dormitoryService.create(new DormitoryCreateDTO("findAllDTO2", emptyRoomList));
        dormitoryService.create(new DormitoryCreateDTO("findAllDTO3", emptyRoomList));
        dormitoryService.create(new DormitoryCreateDTO("findAllDTO4", emptyRoomList));
        dormitoryService.create(new DormitoryCreateDTO("findAllDTO5", emptyRoomList));
        dormitoryService.create(new DormitoryCreateDTO("findAllDTO6", emptyRoomList));
        dormitoryService.create(new DormitoryCreateDTO("findAllDTO7", emptyRoomList));

        int newNumberOfDormitories = dormitoryService.findAllDTO().size();

        assertEquals(oldNumberOfDormitories + 7, newNumberOfDormitories);
    }


    @Test
    @Order(100)
    public void deleteAll() {
        List<DormitoryDTO> allDormitories = dormitoryService.findAllDTO();
        allDormitories.forEach((x) -> dormitoryService.delete(x.getId()));
        assertEquals(0, dormitoryService.findAllDTO().size());
    }
}
