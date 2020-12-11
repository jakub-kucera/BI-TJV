package cz.cvut.fit.tjv.kucerj56.controller;

import cz.cvut.fit.tjv.kucerj56.dto.DormitoryCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.DormitoryDTO;
import cz.cvut.fit.tjv.kucerj56.entity.Dormitory;
import cz.cvut.fit.tjv.kucerj56.service.DormitoryService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
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
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class DormitoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private DormitoryController dormitoryController;

    @MockBean
    private DormitoryService dormitoryService;

    @Test
    void create() throws Exception {
        DormitoryDTO dormitoryDTO = new DormitoryDTO(1,"dormitoryOne", new ArrayList<>());
        DormitoryCreateDTO dormitoryCreateDTO = new DormitoryCreateDTO("dormitoryOne", new ArrayList<>());

        BDDMockito.given(dormitoryService.create(dormitoryCreateDTO)).willReturn(dormitoryDTO);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/dormitories")
                        .accept("application/vhd.suz-dormitories+json")
                        .contentType("application/vhd.suz-dormitories+json")
                        .content("{ \"name\": \"" + dormitoryDTO.getName() + "\", \"roomIds\": [] }")
//                        .content("{ \"name\": \"dormitoryOne\", \"roomIds\": [] } ")
        ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(dormitoryCreateDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomIds", CoreMatchers.is(dormitoryCreateDTO.getRoomIds())));

        Mockito.verify(dormitoryService, Mockito.atLeastOnce()).create(dormitoryCreateDTO);
    }

    @Test
    void readOne() throws Exception {
//        Dormitory dormitoryOne = new Dormitory("dormitoryOne", new ArrayList<>());
        DormitoryDTO dormitoryDTO = new DormitoryDTO(1,"dormitoryOne", new ArrayList<>());

        BDDMockito.given(dormitoryService.findByIdAsDTO(dormitoryDTO.getId())).willReturn(Optional.of(dormitoryDTO));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/dormitories/{id}", dormitoryDTO.getId())
                        .accept("application/vhd.suz-dormitories+json")
                        .contentType("application/vhd.suz-dormitories+json")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(dormitoryDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomIds", CoreMatchers.is(dormitoryDTO.getRoomIds())));

        Mockito.verify(dormitoryService, Mockito.atLeastOnce()).findByIdAsDTO(1);
    }

    @Test
    void readAll() throws Exception {
        DormitoryDTO dormitoryDTOOne = new DormitoryDTO(1,"dormitoryOne", new ArrayList<>());
        DormitoryDTO dormitoryDTOTwo = new DormitoryDTO(2,"dormitoryTwo", new ArrayList<>());

        BDDMockito.given(dormitoryService.findAllDTO()).willReturn(List.of(dormitoryDTOOne, dormitoryDTOTwo));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/dormitories")
                        .accept("application/vhd.suz-dormitories+json")
                        .contentType("application/vhd.suz-dormitories+json")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is(dormitoryDTOOne.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].roomIds", CoreMatchers.is(dormitoryDTOOne.getRoomIds())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", CoreMatchers.is(dormitoryDTOTwo.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].roomIds", CoreMatchers.is(dormitoryDTOTwo.getRoomIds())));

        Mockito.verify(dormitoryService, Mockito.atLeastOnce()).findAllDTO();
    }

    @Test
    void update() throws Exception {
        DormitoryCreateDTO dormitoryCreateDTONew = new DormitoryCreateDTO("dormitoryOne", new ArrayList<>());
        DormitoryDTO dormitoryDTONew = new DormitoryDTO(1,"dormitoryOne", new ArrayList<>());

        BDDMockito.given(dormitoryService.update(1, dormitoryCreateDTONew)).willReturn(dormitoryDTONew);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/api/dormitories/{id}", 1)
                        .accept("application/vhd.suz-dormitories+json")
                        .contentType("application/vhd.suz-dormitories+json")
                        .content("{ \"name\": \"" + dormitoryCreateDTONew.getName() + "\", \"roomIds\": [] }")
//                        .content("{ \"name\": \"dormitoryOne\", \"roomIds\": [] } ")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(dormitoryCreateDTONew.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomIds", CoreMatchers.is(dormitoryCreateDTONew.getRoomIds())));

        Mockito.verify(dormitoryService, Mockito.atLeastOnce()).update(1, dormitoryCreateDTONew);
    }

    @Test
    void delete() throws Exception {
//        BDDMockito.given(dormitoryService.findByIdAsDTO(1)).willReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/dormitories/{id}", "1")
                        .accept("application/vhd.suz-dormitories+json")
                        .contentType("application/vhd.suz-dormitories+json")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(dormitoryService, Mockito.atLeastOnce()).delete(1);
    }
}