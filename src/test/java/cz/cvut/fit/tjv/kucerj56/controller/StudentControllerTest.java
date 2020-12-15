package cz.cvut.fit.tjv.kucerj56.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.cvut.fit.tjv.kucerj56.dto.StudentCreateDTO;
import cz.cvut.fit.tjv.kucerj56.dto.StudentDTO;
import cz.cvut.fit.tjv.kucerj56.service.StudentService;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@JsonFormat(pattern = "yyyy-MM-dd")
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void create() throws Exception {
        StudentDTO studentDTO = new StudentDTO(
                1, "studentOne", new Date(0), 1, 5000.0, new ArrayList<>());
        StudentCreateDTO studentCreateDTO = new StudentCreateDTO(
                "studentOne", new Date(0), 1, 5000.0, new ArrayList<>());

        BDDMockito.given(studentService.create(studentCreateDTO)).willReturn(studentDTO);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/students")
                        .contentType("application/vhd.suz-students+json")
                        .accept("application/vhd.suz-students+json")
//                        .content("{ \"name\": \"" + studentDTO.getName() + "\", \"roomIds\": [ ] }")
                        .content("{\"name\": \"studentOne\", \"dateOfBirth\": 0, \"roomId\": 1, \"stateOfAccount\": 5000.0, \"facultyIds\": []}")
        ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(studentCreateDTO.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth", CoreMatchers.is(studentCreateDTO.getDateOfBirth())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth", CoreMatchers.is("1970-01-01")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomId", CoreMatchers.is(studentCreateDTO.getRoomId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stateOfAccount", CoreMatchers.is(studentCreateDTO.getStateOfAccount())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.facultyIds", CoreMatchers.is(studentCreateDTO.getFacultyIds())));

        Mockito.verify(studentService, Mockito.atLeastOnce()).create(studentCreateDTO);
    }

    @Test
    void readOne() throws Exception {
        StudentDTO studentDTO = new StudentDTO(
                1, "studentOne", new Date(0), 1, 5000.0, new ArrayList<>());

        BDDMockito.given(studentService.findByIdAsDTO(studentDTO.getId())).willReturn(Optional.of(studentDTO));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/students/{id}", studentDTO.getId())
                        .contentType("application/vhd.suz-students+json")
                        .accept("application/vhd.suz-students+json")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(studentDTO.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth", CoreMatchers.is(studentDTO.getDateOfBirth())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth", CoreMatchers.is("1970-01-01")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomId", CoreMatchers.is(studentDTO.getRoomId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stateOfAccount", CoreMatchers.is(studentDTO.getStateOfAccount())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.facultyIds", CoreMatchers.is(studentDTO.getFacultyIds())));


        Mockito.verify(studentService, Mockito.atLeastOnce()).findByIdAsDTO(1);
    }

    @Test
    void readAll() throws Exception {
        StudentDTO studentDTOOne = new StudentDTO(
                1, "studentOne", new Date(0), 1, 5000.0, new ArrayList<>());
        StudentDTO studentDTOTwo = new StudentDTO(
                2, "studentDTOTwo", new Date(1), 2, 1000.0, new ArrayList<>());

        BDDMockito.given(studentService.findAllDTO()).willReturn(List.of(studentDTOOne, studentDTOTwo));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/students")
                        .contentType("application/vhd.suz-students+json")
                        .accept("application/vhd.suz-students+json")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is(studentDTOOne.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateOfBirth", CoreMatchers.is(studentDTOOne.getDateOfBirth())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateOfBirth", CoreMatchers.is("1970-01-01")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].roomId", CoreMatchers.is(studentDTOOne.getRoomId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stateOfAccount", CoreMatchers.is(studentDTOOne.getStateOfAccount())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].facultyIds", CoreMatchers.is(studentDTOOne.getFacultyIds())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", CoreMatchers.is(studentDTOTwo.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dateOfBirth", CoreMatchers.is(studentDTOTwo.getDateOfBirth())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dateOfBirth", CoreMatchers.is("1970-01-01")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].roomId", CoreMatchers.is(studentDTOTwo.getRoomId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].stateOfAccount", CoreMatchers.is(studentDTOTwo.getStateOfAccount())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].facultyIds", CoreMatchers.is(studentDTOTwo.getFacultyIds())));

        Mockito.verify(studentService, Mockito.atLeastOnce()).findAllDTO();
    }

    @Test
    void update() throws Exception {
        StudentDTO studentDTONew = new StudentDTO(
                1, "studentOne", new Date(0L), 1, 5000.0, new ArrayList<>());
        StudentCreateDTO studentCreateDTONew = new StudentCreateDTO(
                "studentOne", new Date(0L), 1, 5000.0, new ArrayList<>());

        BDDMockito.given(studentService.update(1, studentCreateDTONew)).willReturn(studentDTONew);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/api/students/{id}", 1)
                        .contentType("application/vhd.suz-students+json")
                        .accept("application/vhd.suz-students+json")
                        .content("{\"name\": \"studentOne\", \"dateOfBirth\": 0, \"roomId\": 1, \"stateOfAccount\": 5000.0, \"facultyIds\": []}")
//                        .content("{\"name\": \"studentOne\", \"dateOfBirth\": " + new Date(0L) + ", \"roomId\": 1, \"stateOfAccount\": 5000.0, \"facultyIds\": []}")

        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(studentCreateDTONew.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth", CoreMatchers.is(studentCreateDTONew.getDateOfBirth())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth", CoreMatchers.is("1970-01-01")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomId", CoreMatchers.is(studentCreateDTONew.getRoomId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stateOfAccount", CoreMatchers.is(studentCreateDTONew.getStateOfAccount())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.facultyIds", CoreMatchers.is(studentCreateDTONew.getFacultyIds())));

        Mockito.verify(studentService, Mockito.atLeastOnce()).update(1, studentCreateDTONew);
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/students/{id}", "1")
                        .contentType("application/vhd.suz-students+json")
                        .accept("application/vhd.suz-students+json")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(studentService, Mockito.atLeastOnce()).delete(1);
    }

    @Test
    void giveScholarShip() throws Exception {
        StudentDTO studentDTOOne = new StudentDTO(
                1, "studentOne", new Date(0), 1, 5000.0, List.of(1, 2));
        StudentDTO studentDTOOneNew = new StudentDTO(
                1, "studentOne", new Date(0), 1, 6000.0, List.of(1, 2));
        StudentDTO studentDTOTwo = new StudentDTO(
                2, "studentTwo", new Date(1), 2, 1000.0, List.of(2, 3));
        StudentDTO studentDTOTwoNew = new StudentDTO(
                2, "studentTwo", new Date(1), 2, 2000.0, List.of(2, 3));
        StudentDTO studentDTOThree = new StudentDTO(
                3, "studentThree", new Date(0), 3, 10000.0, List.of(1));

        BDDMockito.given(studentService.giveScholarShip(1000.0)).willReturn(List.of(studentDTOOneNew, studentDTOTwoNew));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/api/students/giveScholarShip/{amount}", 1000.0)
                        .contentType("application/vhd.suz-students+json")
                        .accept("application/vhd.suz-students+json")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is(studentDTOOneNew.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateOfBirth", CoreMatchers.is(studentDTOOne.getDateOfBirth())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateOfBirth", CoreMatchers.is("1970-01-01")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].roomId", CoreMatchers.is(studentDTOOneNew.getRoomId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stateOfAccount", CoreMatchers.is(studentDTOOneNew.getStateOfAccount())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].facultyIds", CoreMatchers.is(studentDTOOneNew.getFacultyIds())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", CoreMatchers.is(studentDTOTwoNew.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dateOfBirth", CoreMatchers.is(studentDTOTwo.getDateOfBirth())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dateOfBirth", CoreMatchers.is("1970-01-01")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].roomId", CoreMatchers.is(studentDTOTwoNew.getRoomId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].stateOfAccount", CoreMatchers.is(studentDTOTwoNew.getStateOfAccount())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].facultyIds", CoreMatchers.is(studentDTOTwoNew.getFacultyIds())));

        Mockito.verify(studentService, Mockito.atLeastOnce()).giveScholarShip(1000.0);
    }
}