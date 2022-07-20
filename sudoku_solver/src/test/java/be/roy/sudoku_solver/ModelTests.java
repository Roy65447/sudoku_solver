package be.roy.sudoku_solver;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
                int[][] unsolved = {
                                { 0, 0, 0, 2, 6, 0, 7, 0, 1 },
                                { 6, 8, 0, 0, 7, 0, 0, 9, 0 },
                                { 1, 9, 0, 0, 0, 4, 5, 0, 0 },
                                { 8, 2, 0, 1, 0, 0, 0, 4, 0 },
                                { 0, 0, 4, 6, 0, 2, 9, 0, 0 },
                                { 0, 5, 0, 0, 0, 3, 0, 2, 8 },
                                { 0, 0, 9, 3, 0, 0, 0, 7, 4 },
                                { 0, 4, 0, 0, 5, 0, 0, 3, 6 },
                                { 7, 0, 3, 0, 1, 8, 0, 0, 0 }
                };
                sudoku = new Sudoku(unsolved);
        }

        @Test
        void testSetValue() {
                square.setValue(6);
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