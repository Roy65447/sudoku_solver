package be.roy.sudoku_solver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import be.roy.models.Square;
import be.roy.models.Sudoku;
import be.roy.services.SudokuSolverService;

@SpringBootTest
public class SudokuSolverTests {
    private static Sudoku sudoku;
    private SudokuSolverService sudokuSolverService = new SudokuSolverService();;

    @BeforeAll
    static void initObjects() {
        Square[][] unsolved = {
                { new Square(0), new Square(0), new Square(0), new Square(2), new Square(6), new Square(0),
                        new Square(7), new Square(0), new Square(1) },
                { new Square(6), new Square(8), new Square(0), new Square(0), new Square(7), new Square(0),
                        new Square(0), new Square(9), new Square(0) },
                { new Square(1), new Square(9), new Square(0), new Square(0), new Square(0), new Square(4),
                        new Square(5), new Square(0), new Square(0) },
                { new Square(8), new Square(2), new Square(0), new Square(1), new Square(0), new Square(0),
                        new Square(0), new Square(4), new Square(0) },
                { new Square(0), new Square(0), new Square(4), new Square(6), new Square(0), new Square(2),
                        new Square(9), new Square(0), new Square(0) },
                { new Square(0), new Square(5), new Square(0), new Square(0), new Square(0), new Square(3),
                        new Square(0), new Square(2), new Square(8) },
                { new Square(0), new Square(0), new Square(9), new Square(3), new Square(0), new Square(0),
                        new Square(0), new Square(7), new Square(4) },
                { new Square(0), new Square(4), new Square(0), new Square(0), new Square(5), new Square(0),
                        new Square(0), new Square(3), new Square(6) },
                { new Square(7), new Square(0), new Square(3), new Square(0), new Square(1), new Square(8),
                        new Square(0), new Square(0), new Square(0) } };
        sudoku = new Sudoku(unsolved);
    }

    @Test
    void testSolveSudoku() {
        assertEquals(6, sudoku.getSolved()[1][0].getValue());
        assertEquals(2, sudoku.getSolved()[4][5].getValue());
        assertEquals(3, sudoku.getSolved()[7][7].getValue());
        System.out.println(sudoku.toString());
        sudokuSolverService.solveSudoku(sudoku);
        System.out.println(sudoku.toString());
        assertEquals(4, sudoku.getSolved()[0][0].getValue());
        assertEquals(8, sudoku.getSolved()[4][4].getValue());
        assertEquals(9, sudoku.getSolved()[8][8].getValue());
    }
}
