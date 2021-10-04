package be.roy.sudoku_solver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import be.roy.sudoku_solver.models.Square;
import be.roy.sudoku_solver.models.Sudoku;

@SpringBootTest
public class ModelTests {
        private static Square square;
        private static Sudoku sudoku;

        @BeforeAll
        static void initObjects() {
                square = new Square();
                Square[][] unsolved = {
                                { new Square(0), new Square(0), new Square(0), new Square(2), new Square(6),
                                                new Square(0), new Square(7), new Square(0), new Square(1) },
                                { new Square(6), new Square(8), new Square(0), new Square(0), new Square(7),
                                                new Square(0), new Square(0), new Square(9), new Square(0) },
                                { new Square(1), new Square(9), new Square(0), new Square(0), new Square(0),
                                                new Square(4), new Square(5), new Square(0), new Square(0) },
                                { new Square(8), new Square(2), new Square(0), new Square(1), new Square(0),
                                                new Square(0), new Square(0), new Square(4), new Square(0) },
                                { new Square(0), new Square(0), new Square(4), new Square(6), new Square(0),
                                                new Square(2), new Square(9), new Square(0), new Square(0) },
                                { new Square(0), new Square(5), new Square(0), new Square(0), new Square(0),
                                                new Square(3), new Square(0), new Square(2), new Square(8) },
                                { new Square(0), new Square(0), new Square(9), new Square(3), new Square(0),
                                                new Square(0), new Square(0), new Square(7), new Square(4) },
                                { new Square(0), new Square(4), new Square(0), new Square(0), new Square(5),
                                                new Square(0), new Square(0), new Square(3), new Square(6) },
                                { new Square(7), new Square(0), new Square(3), new Square(0), new Square(1),
                                                new Square(8), new Square(0), new Square(0), new Square(0) } };
                sudoku = new Sudoku(unsolved);
        }

        @Test
        void testRemoveHint() {
                square.setHints(new ArrayList<Integer>(Arrays.asList(3)));
                square.removeHint(3);
                assertEquals(square.getHints(), new ArrayList<Integer>());
        }

        @Test
        void testSetValue() {
                square.setHints(new ArrayList<Integer>(Arrays.asList(3, 6, 9)));
                square.setValue(6);
                assertEquals(0, square.getHints().size());
                assertEquals(6, square.getValue());
        }

        @Test
        void testSudoku() {
                assertEquals(6, sudoku.getSolved()[1][0].getValue());
                assertEquals(2, sudoku.getSolved()[4][5].getValue());
                assertEquals(3, sudoku.getSolved()[7][7].getValue());
                System.out.println(sudoku.toString());
        }
}