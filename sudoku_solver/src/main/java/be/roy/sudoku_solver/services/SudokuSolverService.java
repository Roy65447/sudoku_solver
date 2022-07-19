package be.roy.sudoku_solver.services;

import org.springframework.stereotype.Service;
import be.roy.sudoku_solver.models.Square;
import be.roy.sudoku_solver.models.Sudoku;
import lombok.var;

/**
 * Service that solves an unsolved sudoku
 */
@Service
public class SudokuSolverService {
    Sudoku solvedSudoku;

    public Sudoku solveSudoku(Sudoku sudoku) {
        solvedSudoku = sudoku;
        var incompleteSudoku = sudoku.getSolved();
        incompleteSudoku[0][0].getValue();
        int sudokuSize = incompleteSudoku.length;
        if (backtrack(incompleteSudoku, sudokuSize)) {
            return solvedSudoku;
        }
        return sudoku;
    }

    private boolean backtrack(Square[][] sudoku, int sudokuSize) {
        int row = -1;
        int col = -1;
        boolean solved = true;
        for (int i = 0; i < sudokuSize; i++) {
            for (int j = 0; j < sudokuSize; j++) {
                if (sudoku[i][j].getValue() == 0) {
                    row = i;
                    col = j;
                    // still not solved
                    solved = false;
                    break;
                }
                if (!solved)
                    break;
            }
        }

        if (solved)
            return true;

        // if not solved backtrack
        for (int i = 1; i <= sudokuSize; i++) {
            if (isSafe(sudoku, row, col, i)) {
                sudoku[row][col].setValue(i);
                if (backtrack(sudoku, sudokuSize)) {
                    solvedSudoku.setSolved(sudoku);
                    return true;
                } else {
                    // replace value with 0 to backtrack until correct number is placed
                    sudoku[row][col].setValue(0);
                }
            }
        }
        return false;
    }

    private boolean isSafe(Square[][] sudoku, int row, int col, int value) {
        if (checkRowAndColumnClash(sudoku, row, col, value) || checkBoxClash(sudoku, row, col, value))
            return false;
        // no clash
        return true;
    }

    // check if the number already occurs in the same row/col
    private boolean checkRowAndColumnClash(Square[][] sudoku, int row, int col, int value) {
        for (int i = 0; i < sudoku.length; i++) {
            if (sudoku[row][i].getValue() == value)
                return true;
            if (sudoku[i][col].getValue() == value)
                return true;
        }
        return false;
    }

    // check if the number occurs in the same 3x3 grid
    private boolean checkBoxClash(Square[][] sudoku, int row, int col, int value) {
        int dimension = (int) Math.sqrt(sudoku.length);
        int boxRowStart = row - row % dimension;
        int boxColStart = col - col % dimension;

        for (int i = boxRowStart; i < boxRowStart + dimension; i++) {
            for (int j = boxColStart; j < boxColStart + dimension; j++) {
                if (sudoku[i][j].getValue() == value)
                    return true;
            }
        }
        return false;
    }
}
