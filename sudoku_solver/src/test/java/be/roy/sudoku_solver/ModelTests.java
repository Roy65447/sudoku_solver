package be.roy.sudoku_solver;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import be.roy.models.Square;

@SpringBootTest
public class ModelTests {
    private Square square;

    @BeforeEach
    void initObjects() {
        square = new Square();
    }

    @Test
    void testAddHint() {
        square.addHint(3);
        assertArrayEquals(new ArrayList<Integer>(Arrays.asList(3)).toArray(), square.getHints().toArray());
    }

    @Test
    void testAddHintAndRemoveHint() {
        square.addHint(3);
        assertArrayEquals(new ArrayList<Integer>(Arrays.asList(3)).toArray(), square.getHints().toArray());
        square.removeHint(3);
        assertEquals(square.getHints(), new ArrayList<Integer>());
    }

    @Test
    void testSetValue() {
        square.addHint(3);
        square.addHint(6);
        square.addHint(9);
        assertEquals(3, square.getHints().size());
        square.setValue(6);
        assertEquals(0, square.getHints().size());
        assertEquals(6, square.getValue());
    }
}