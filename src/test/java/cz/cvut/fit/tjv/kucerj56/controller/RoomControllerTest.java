package cz.cvut.fit.tjv.kucerj56.controller;

import cz.cvut.fit.tjv.kucerj56.dto.RoomCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.RoomDTO;
import cz.cvut.fit.tjv.kucerj56.service.RoomService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private RoomController roomController;

    @MockBean
    private RoomService roomService;

    @Test
    void create() throws Exception {
        RoomDTO roomDTO = new RoomDTO(1, 10, false, 80.0, 2, 1, new ArrayList<>());
        RoomCreateDTO roomCreateDTO = new RoomCreateDTO(10, false, 80.0, 2, 1, new ArrayList<>());

        BDDMockito.given(roomService.create(roomCreateDTO)).willReturn(roomDTO);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/rooms")
                        .contentType("application/vhd.suz-rooms+json")
                        .accept("application/vhd.suz-rooms+json")
//                        .content("{ \"name\": \"" + roomDTO.getName() + "\", \"roomIds\": [ ] }")
                        .content("{ \"numberRoom\": 10, \"quarantineRoom\": 0, \"pricePerDay\": 80.0, \"numberOfBeds\": 2, \"dormitoryId\": 1, \"studentIds\": [] }")
        ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberRoom", CoreMatchers.is(roomCreateDTO.getNumberRoom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quarantineRoom", CoreMatchers.is(roomCreateDTO.getQuarantineRoom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pricePerDay", CoreMatchers.is(roomCreateDTO.getPricePerDay())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfBeds", CoreMatchers.is(roomCreateDTO.getNumberOfBeds())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dormitoryId", CoreMatchers.is(roomCreateDTO.getDormitoryId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quarantineRoom", CoreMatchers.is(roomCreateDTO.getQuarantineRoom())));

        Mockito.verify(roomService, Mockito.atLeastOnce()).create(roomCreateDTO);
    }

    @Test
    void readOne() throws Exception {
//        Room roomOne = new Room("roomOne", new ArrayList<>());
        RoomDTO roomDTO = new RoomDTO(1, 10, false, 80.0, 2, 1, new ArrayList<>());

        BDDMockito.given(roomService.findByIdAsDTO(roomDTO.getId())).willReturn(Optional.of(roomDTO));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/rooms/{id}", roomDTO.getId())
                        .contentType("application/vhd.suz-rooms+json")
                        .accept("application/vhd.suz-rooms+json")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberRoom", CoreMatchers.is(roomDTO.getNumberRoom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quarantineRoom", CoreMatchers.is(roomDTO.getQuarantineRoom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pricePerDay", CoreMatchers.is(roomDTO.getPricePerDay())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfBeds", CoreMatchers.is(roomDTO.getNumberOfBeds())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dormitoryId", CoreMatchers.is(roomDTO.getDormitoryId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quarantineRoom", CoreMatchers.is(roomDTO.getQuarantineRoom())));

        Mockito.verify(roomService, Mockito.atLeastOnce()).findByIdAsDTO(1);
    }

    @Test
    void readAll() throws Exception {
        RoomDTO roomDTOOne = new RoomDTO(1, 10, false, 80.0, 2, 1, new ArrayList<>());
        RoomDTO roomDTOTwo = new RoomDTO(1, 20, false, 100.0, 5, 2, new ArrayList<>());

        BDDMockito.given(roomService.findAllDTO()).willReturn(List.of(roomDTOOne, roomDTOTwo));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/rooms")
                        .contentType("application/vhd.suz-rooms+json")
                        .accept("application/vhd.suz-rooms+json")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numberRoom", CoreMatchers.is(roomDTOOne.getNumberRoom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quarantineRoom", CoreMatchers.is(roomDTOOne.getQuarantineRoom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].pricePerDay", CoreMatchers.is(roomDTOOne.getPricePerDay())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numberOfBeds", CoreMatchers.is(roomDTOOne.getNumberOfBeds())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dormitoryId", CoreMatchers.is(roomDTOOne.getDormitoryId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quarantineRoom", CoreMatchers.is(roomDTOOne.getQuarantineRoom())))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].numberRoom", CoreMatchers.is(roomDTOTwo.getNumberRoom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].quarantineRoom", CoreMatchers.is(roomDTOTwo.getQuarantineRoom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].pricePerDay", CoreMatchers.is(roomDTOTwo.getPricePerDay())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].numberOfBeds", CoreMatchers.is(roomDTOTwo.getNumberOfBeds())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dormitoryId", CoreMatchers.is(roomDTOTwo.getDormitoryId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].quarantineRoom", CoreMatchers.is(roomDTOTwo.getQuarantineRoom())));

        Mockito.verify(roomService, Mockito.atLeastOnce()).findAllDTO();
    }

    @Test
    void update() throws Exception {
        RoomDTO roomDTONew = new RoomDTO(1, 10, false, 80.0, 2, 1, new ArrayList<>());
        RoomCreateDTO roomCreateDTONew = new RoomCreateDTO(10, false, 80.0, 2, 1, new ArrayList<>());

        BDDMockito.given(roomService.update(1, roomCreateDTONew)).willReturn(roomDTONew);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/api/rooms/{id}", 1)
                        .contentType("application/vhd.suz-rooms+json")
                        .accept("application/vhd.suz-rooms+json")
//                        .content("{ \"name\": \"" + roomCreateDTONew.getName() + "\", \"roomIds\": [ ] }")
                        .content("{ \"numberRoom\": 10, \"quarantineRoom\": 0, \"pricePerDay\": 80.0, \"numberOfBeds\": 2, \"dormitoryId\": 1, \"studentIds\": [] }")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberRoom", CoreMatchers.is(roomCreateDTONew.getNumberRoom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quarantineRoom", CoreMatchers.is(roomCreateDTONew.getQuarantineRoom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pricePerDay", CoreMatchers.is(roomCreateDTONew.getPricePerDay())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfBeds", CoreMatchers.is(roomCreateDTONew.getNumberOfBeds())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dormitoryId", CoreMatchers.is(roomCreateDTONew.getDormitoryId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quarantineRoom", CoreMatchers.is(roomCreateDTONew.getQuarantineRoom())));

        Mockito.verify(roomService, Mockito.atLeastOnce()).update(1, roomCreateDTONew);
    }

    @Test
    void delete() throws Exception {
//        BDDMockito.given(roomService.findByIdAsDTO(1)).willReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/rooms/{id}", "1")
                        .contentType("application/vhd.suz-rooms+json")
                        .accept("application/vhd.suz-rooms+json")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(roomService, Mockito.atLeastOnce()).delete(1);
    }
}