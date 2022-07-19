package be.roy.sudoku_solver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.roy.sudoku_solver.models.Square;
import be.roy.sudoku_solver.models.Sudoku;
import be.roy.sudoku_solver.services.SudokuSolverService;

@SpringBootTest
public class SudokuSolverTests {
        private static Sudoku easySudoku;
        private static Sudoku difficultSudoku;
        @Autowired
        private SudokuSolverService sudokuSolverService;

        @BeforeAll
        static void initObjects() {
                Square[][] easyUnsolved = {
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
                Square[][] difficultUnsolved = {
                                { new Square(2), new Square(0), new Square(0), new Square(3), new Square(0),
                                                new Square(0), new Square(0), new Square(0), new Square(0) },
                                { new Square(8), new Square(0), new Square(4), new Square(0), new Square(6),
                                                new Square(2), new Square(0), new Square(0), new Square(3) },
                                { new Square(0), new Square(1), new Square(3), new Square(8), new Square(0),
                                                new Square(0), new Square(2), new Square(0), new Square(0) },
                                { new Square(0), new Square(0), new Square(0), new Square(0), new Square(2),
                                                new Square(0), new Square(3), new Square(9), new Square(0) },
                                { new Square(5), new Square(0), new Square(7), new Square(0), new Square(0),
                                                new Square(0), new Square(6), new Square(2), new Square(1) },
                                { new Square(0), new Square(3), new Square(2), new Square(0), new Square(0),
                                                new Square(6), new Square(0), new Square(0), new Square(0) },
                                { new Square(0), new Square(2), new Square(0), new Square(0), new Square(0),
                                                new Square(9), new Square(1), new Square(4), new Square(0) },
                                { new Square(6), new Square(0), new Square(1), new Square(2), new Square(5),
                                                new Square(0), new Square(8), new Square(0), new Square(9) },
                                { new Square(0), new Square(0), new Square(0), new Square(0), new Square(0),
                                                new Square(1), new Square(0), new Square(0), new Square(2) } };
                easySudoku = new Sudoku(easyUnsolved);
                difficultSudoku = new Sudoku(difficultUnsolved);

        }

        @Test
        void testSolveSudoku() {
                assertEquals(6, easySudoku.getSolved()[1][0].getValue());
                assertEquals(2, easySudoku.getSolved()[4][5].getValue());
                assertEquals(3, easySudoku.getSolved()[7][7].getValue());
                sudokuSolverService.solveSudoku(easySudoku);
                System.out.println(easySudoku.toString());
                assertEquals(4, easySudoku.getSolved()[0][0].getValue());
                assertEquals(8, easySudoku.getSolved()[4][4].getValue());
                assertEquals(9, easySudoku.getSolved()[8][8].getValue());
                sudokuSolverService.solveSudoku(difficultSudoku);
                System.out.println(difficultSudoku.toString());
        }

        @Test
        void testSolveNotFunSudoku() {
                Square[][] notFunUnsolved = {
                        { new Square(0), new Square(2), new Square(0), new Square(0), new Square(0),
                                new Square(0), new Square(0), new Square(0), new Square(0) },
                { new Square(0), new Square(0), new Square(0), new Square(6), new Square(0),
                                new Square(0), new Square(0), new Square(0), new Square(3) },
                { new Square(0), new Square(7), new Square(4), new Square(0), new Square(8),
                                new Square(0), new Square(0), new Square(0), new Square(0) },
                { new Square(0), new Square(0), new Square(0), new Square(0), new Square(0),
                                new Square(3), new Square(0), new Square(0), new Square(2) },
                { new Square(0), new Square(8), new Square(0), new Square(0), new Square(4),
                                new Square(0), new Square(0), new Square(1), new Square(0) },
                { new Square(6), new Square(0), new Square(0), new Square(5), new Square(0),
                                new Square(0), new Square(0), new Square(0), new Square(0) },
                { new Square(0), new Square(0), new Square(0), new Square(0), new Square(1),
                                new Square(0), new Square(7), new Square(8), new Square(0) },
                { new Square(5), new Square(0), new Square(0), new Square(0), new Square(0),
                                new Square(9), new Square(0), new Square(0), new Square(0) },
                { new Square(0), new Square(0), new Square(0), new Square(0), new Square(0),
                                new Square(0), new Square(0), new Square(4), new Square(0) } 
                };
                var notFunSudoku = new Sudoku(notFunUnsolved);
                sudokuSolverService.solveSudoku(notFunSudoku);
                System.out.println(notFunSudoku);
        }
}
