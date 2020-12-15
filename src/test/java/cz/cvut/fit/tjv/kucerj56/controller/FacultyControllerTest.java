package cz.cvut.fit.tjv.kucerj56.controller;

import cz.cvut.fit.tjv.kucerj56.dto.FacultyCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.FacultyDTO;
import cz.cvut.fit.tjv.kucerj56.service.FacultyService;
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
class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Test
    void create() throws Exception {
        FacultyDTO facultyDTO = new FacultyDTO(1, "facultyOne", new ArrayList<>());
        FacultyCreateDTO facultyCreateDTO = new FacultyCreateDTO("facultyOne", new ArrayList<>());

        BDDMockito.given(facultyService.create(facultyCreateDTO)).willReturn(facultyDTO);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/faculties")
                        .contentType("application/vhd.suz-faculties+json")
                        .accept("application/vhd.suz-faculties+json")
//                        .content("{ \"name\": \"" + facultyDTO.getName() + "\", \"roomIds\": [ ] }")
                        .content("{\"name\": \"facultyOne\", \"studentIds\": []}")
        ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(facultyCreateDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentIds", CoreMatchers.is(facultyCreateDTO.getStudentIds())));

        Mockito.verify(facultyService, Mockito.atLeastOnce()).create(facultyCreateDTO);
    }

    @Test
    void readOne() throws Exception {
        FacultyDTO facultyDTO = new FacultyDTO(1, "facultyOne", new ArrayList<>());

        BDDMockito.given(facultyService.findByIdAsDTO(facultyDTO.getId())).willReturn(Optional.of(facultyDTO));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/faculties/{id}", facultyDTO.getId())
                        .contentType("application/vhd.suz-faculties+json")
                        .accept("application/vhd.suz-faculties+json")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(facultyDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentIds", CoreMatchers.is(facultyDTO.getStudentIds())));

        Mockito.verify(facultyService, Mockito.atLeastOnce()).findByIdAsDTO(1);
    }

    @Test
    void readAll() throws Exception {
        FacultyDTO facultyDTOOne = new FacultyDTO(1, "facultyOne", new ArrayList<>());
        FacultyDTO facultyDTOTwo = new FacultyDTO(2, "facultyTwo", new ArrayList<>());

        BDDMockito.given(facultyService.findAllDTO()).willReturn(List.of(facultyDTOOne, facultyDTOTwo));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/faculties")
                        .contentType("application/vhd.suz-faculties+json")
                        .accept("application/vhd.suz-faculties+json")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is(facultyDTOOne.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].studentIds", CoreMatchers.is(facultyDTOOne.getStudentIds())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", CoreMatchers.is(facultyDTOTwo.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].studentIds", CoreMatchers.is(facultyDTOTwo.getStudentIds())));

        Mockito.verify(facultyService, Mockito.atLeastOnce()).findAllDTO();
    }

    @Test
    void update() throws Exception {
        FacultyCreateDTO facultyCreateDTONew = new FacultyCreateDTO("facultyOne", new ArrayList<>());
        FacultyDTO facultyDTONew = new FacultyDTO(1, "facultyOne", new ArrayList<>());

        BDDMockito.given(facultyService.update(1, facultyCreateDTONew)).willReturn(facultyDTONew);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/api/faculties/{id}", 1)
                        .contentType("application/vhd.suz-faculties+json")
                        .accept("application/vhd.suz-faculties+json")
//                        .content("{ \"name\": \"" + facultyCreateDTONew.getName() + "\", \"roomIds\": [ ] }")
                        .content("{ \"name\": \"facultyOne\", \"studentIds\": [ ] } ")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(facultyCreateDTONew.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentIds", CoreMatchers.is(facultyCreateDTONew.getStudentIds())));

        Mockito.verify(facultyService, Mockito.atLeastOnce()).update(1, facultyCreateDTONew);
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/faculties/{id}", "1")
                        .contentType("application/vhd.suz-faculties+json")
                        .accept("application/vhd.suz-faculties+json")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(facultyService, Mockito.atLeastOnce()).delete(1);
    }
}