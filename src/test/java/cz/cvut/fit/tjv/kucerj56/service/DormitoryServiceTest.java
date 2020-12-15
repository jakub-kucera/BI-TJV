package cz.cvut.fit.tjv.kucerj56.service;

import cz.cvut.fit.tjv.kucerj56.dto.DormitoryCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.DormitoryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DormitoryServiceTest {
    private final DormitoryService dormitoryService;

    @Autowired
    public DormitoryServiceTest(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }


    //    @Test
    public void findNone() {
        assertEquals(dormitoryService.findAllDTO().size(), 0);
    }

    @Test
    public void create() {
        List<Integer> emptyRoomList = new ArrayList<>();
        DormitoryCreateDTO newDormitory = new DormitoryCreateDTO("createTest", emptyRoomList);
        DormitoryDTO createdDormitory = dormitoryService.create(newDormitory);
        assertTrue(
                newDormitory.getName().equals(createdDormitory.getName()) &&
                        newDormitory.getRoomIds().equals(createdDormitory.getRoomIds()));
    }

    @Test
    public void findByAsDTOId() {
        List<Integer> emptyRoomList = new ArrayList<>();
        DormitoryCreateDTO newDormitory = new DormitoryCreateDTO("findByAsDTOId", emptyRoomList);
        DormitoryDTO createdDormitory = dormitoryService.create(newDormitory);
        Optional<DormitoryDTO> foundDormitoryDTOOptional = dormitoryService.findByIdAsDTO(createdDormitory.getId());

        DormitoryDTO foundDormitoryDTO = foundDormitoryDTOOptional.orElseThrow(AssertionError::new);

        assertEquals(foundDormitoryDTO, createdDormitory);


    }

    @Test
    public void update() {
        List<Integer> emptyRoomList = new ArrayList<>();
        DormitoryCreateDTO newDormitory = new DormitoryCreateDTO("oldUpdate", emptyRoomList);
        DormitoryDTO createdDormitory = dormitoryService.create(newDormitory);

        DormitoryCreateDTO newUpdatedDormitory = new DormitoryCreateDTO("newUpdate", emptyRoomList);
        DormitoryDTO updatedDormitory = dormitoryService.update(createdDormitory.getId(), newUpdatedDormitory);

        Optional<DormitoryDTO> foundUpdatedDormitoryDTOOptional = dormitoryService.findByIdAsDTO(createdDormitory.getId());

        DormitoryDTO foundUpdatedDormitoryDTO = foundUpdatedDormitoryDTOOptional.orElseThrow(AssertionError::new);

        assertEquals(updatedDormitory, foundUpdatedDormitoryDTO);

    }

    @Test
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
    public void deleteAll() {
        List<DormitoryDTO> allDormitories = dormitoryService.findAllDTO();
        allDormitories.forEach((x) -> dormitoryService.delete(x.getId()));
        assertEquals(0, dormitoryService.findAllDTO().size());
    }

}
