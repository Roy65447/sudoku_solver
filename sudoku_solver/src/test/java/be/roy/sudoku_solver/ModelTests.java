package be.roy.sudoku_solver;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import be.roy.models.Square;

@SpringBootTest
public class ModelTests {
    private Square square;

    @Test
    void testAddhint() {
        square = new Square();
        square.addHint(3);
        assertArrayEquals(square.getHints().toArray(), new ArrayList<Integer>(Arrays.asList(3)).toArray());
    }

    @Test
    void testHintsEditting() {
        square = new Square();
        square.addHint(3);
        assertArrayEquals(square.getHints().toArray(), new ArrayList<Integer>(Arrays.asList(3)).toArray());
        square.removeHint(3);
        assertEquals(square.getHints(), new ArrayList<Integer>());
    }
}