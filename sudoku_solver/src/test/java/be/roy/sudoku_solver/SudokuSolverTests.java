package be.roy.sudoku_solver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
                int[][] easyUnsolved = {
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
                int[][] difficultUnsolved = {
                                { 2, 0, 0, 3, 0, 0, 0, 0, 0 },
                                { 8, 0, 4, 0, 6, 2, 0, 0, 3 },
                                { 0, 1, 3, 8, 0, 0, 2, 0, 0 },
                                { 0, 0, 0, 0, 2, 0, 3, 9, 0 },
                                { 5, 0, 7, 0, 0, 0, 6, 2, 1 },
                                { 0, 3, 2, 0, 0, 6, 0, 0, 0 },
                                { 0, 2, 0, 0, 0, 9, 1, 4, 0 },
                                { 6, 0, 1, 2, 5, 0, 8, 0, 9 },
                                { 0, 0, 0, 0, 0, 1, 0, 0, 2 }
                };

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
                int[][] notFunUnsolved = {
                                { 0, 2, 0, 0, 0, 0, 0, 0, 0 },
                                { 0, 0, 0, 6, 0, 0, 0, 0, 3 },
                                { 0, 7, 4, 0, 8, 0, 0, 0, 0 },
                                { 0, 0, 0, 0, 0, 3, 0, 0, 2 },
                                { 0, 8, 0, 0, 4, 0, 0, 1, 0 },
                                { 6, 0, 0, 5, 0, 0, 0, 0, 0 },
                                { 0, 0, 0, 0, 1, 0, 7, 8, 0 },
                                { 5, 0, 0, 0, 0, 9, 0, 0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 4, 0 }
                };
                var notFunSudoku = new Sudoku(notFunUnsolved);
                sudokuSolverService.solveSudoku(notFunSudoku);
                assertEquals(1, notFunSudoku.getSolved()[0][0].getValue());
                assertEquals(6, notFunSudoku.getSolved()[4][5].getValue());
                assertEquals(9, notFunSudoku.getSolved()[8][8].getValue());
                System.out.println(notFunSudoku);
        }
}
