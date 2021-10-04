package be.roy.sudoku_solver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.roy.sudoku_solver.models.Square;
import be.roy.sudoku_solver.models.Sudoku;
import be.roy.sudoku_solver.utils.BoundaryCalc;
import lombok.var;

/**
 * Service that solves an unsolved sudoku
 */
@Service
public class SudokuSolverService {
    @Autowired
    private HintService hintService;

    public Sudoku solveSudoku(Sudoku sudoku) {
        sudoku.setSolved(hintService.filterHints(sudoku.getSolved(), sudoku.getDIMENSION()));
        sudoku.setSolved(replaceSingleHints(sudoku));
        return sudoku;
    }

    private Square[][] replaceSingleHints(Sudoku sudoku) {
        var incompleteSudoku = sudoku.getSolved();
        for (int row = 0; row < incompleteSudoku.length; row++) {
            for (int col = 0; col < incompleteSudoku[row].length; col++) {
                if (incompleteSudoku[row][col].getHints().size() == 1) {
                    incompleteSudoku = replaceAndRemoveValueFromHints(incompleteSudoku, row, col,
                            sudoku.getDIMENSION());
                }
            }
        }
        return incompleteSudoku;
    }

    private Square[][] replaceAndRemoveValueFromHints(Square[][] incompleteSudoku, int row, int col,
            final int DIMENSION) {
        var value = incompleteSudoku[row][col].getHints().get(0);
        incompleteSudoku[row][col].setValue(value);
        incompleteSudoku = searchGrid(incompleteSudoku, row, col, DIMENSION, value);
        incompleteSudoku = searchAxes(incompleteSudoku, row, col, DIMENSION, value);
        return incompleteSudoku;
    }

    private Square[][] searchGrid(Square[][] incompleteSudoku, int row, int col, final int DIMENSION, int value) {
        var bounds = BoundaryCalc.calculateBounds(row, col, DIMENSION); // [start index for row, start index for col]
        for (int i = bounds[0]; i < bounds[0] + DIMENSION; i++) {
            for (int j = bounds[1]; j < bounds[1] + DIMENSION; j++) {
                if (incompleteSudoku[i][j].getValue() == 0) {
                    incompleteSudoku[i][j].getHints().removeIf(hint -> hint == value);
                    if (incompleteSudoku[i][j].getHints().size() == 1) {
                        replaceAndRemoveValueFromHints(incompleteSudoku, i, j, DIMENSION);
                    }
                }
            }
        }
        return incompleteSudoku;
    }

    private Square[][] searchAxes(Square[][] incompleteSudoku, int row, int col, final int DIMENSION, int value) {
        incompleteSudoku = searchVerticalAxis(incompleteSudoku, row, col, DIMENSION, value);
        incompleteSudoku = searchHorizontalAxis(incompleteSudoku, row, col, DIMENSION, value);
        return incompleteSudoku;
    }

    private Square[][] searchVerticalAxis(Square[][] incompleteSudoku, int row, int col, final int DIMENSION,
            int value) {
        for (int i = 0; i < incompleteSudoku.length; i++) {
            if (incompleteSudoku[i][col].getValue() == 0) {
                incompleteSudoku[i][col].getHints().removeIf(hint -> hint == value);
                if (incompleteSudoku[i][col].getHints().size() == 1) {
                    replaceAndRemoveValueFromHints(incompleteSudoku, i, col, DIMENSION);
                }
            }
        }
        return incompleteSudoku;
    }

    private Square[][] searchHorizontalAxis(Square[][] incompleteSudoku, int row, int col, final int DIMENSION,
            int value) {
        for (int i = 0; i < incompleteSudoku.length; i++) {
            if (incompleteSudoku[row][i].getValue() == 0) {
                incompleteSudoku[row][i].getHints().removeIf(hint -> hint == value);
                if (incompleteSudoku[row][i].getHints().size() == 1) {
                    replaceAndRemoveValueFromHints(incompleteSudoku, row, i, DIMENSION);
                }
            }
        }
        return incompleteSudoku;
    }
}
