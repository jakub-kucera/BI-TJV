package cz.cvut.fit.tjv.kucerj56;

import cz.cvut.fit.tjv.kucerj56.service.FacultyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DatabaseTest {

    private final FacultyService facultyService;

    @Autowired
    public DatabaseTest(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Test
    public void insertTest() {
        assertEquals(1,1);
    }
}
