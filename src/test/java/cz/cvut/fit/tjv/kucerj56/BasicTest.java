package cz.cvut.fit.tjv.kucerj56;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BasicTest {
    @Test
    public void demoTest() {
        assertEquals(1,1);
    }
}
