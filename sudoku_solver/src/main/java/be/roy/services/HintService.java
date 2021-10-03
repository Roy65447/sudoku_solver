package be.roy.services;

import java.util.ArrayList;
import java.util.TreeSet;
import org.springframework.stereotype.Service;
import be.roy.models.Square;
import be.roy.utils.BoundaryCalc;
import lombok.var;

/**
 * Service dedicated to adding and removing hints to squares
 */
@Service
public class HintService {
    /**
     * Looks for every possible value possible in a square and puts them in the
     * hints arraylist
     * 
     * @param incompleteSudoku - the sudoku that needs to be solved
     * @param DIMENSION        - dimensions of the sudoku which is always 3x3
     * @return incompleteSudoku
     */
    Square[][] filterHints(Square[][] incompleteSudoku, final int DIMENSION) {
        for (int row = 0; row < incompleteSudoku.length; row++) {
            for (int col = 0; col < incompleteSudoku[col].length; col++) {
                var square = incompleteSudoku[row][col];
                if (square.getValue() == 0) {
                    square = removeHints(searchSudokuValues(incompleteSudoku, row, col, DIMENSION), square);
                    incompleteSudoku[row][col] = square;
                }
            }
        }
        return incompleteSudoku;
    }

    private Square removeHints(TreeSet<Integer> unavailableValues, Square square) {
        for (Integer value : unavailableValues) {
            if (square.getHints().contains(value)) {
                square.removeHint(value);
            }
        }
        return square;
    }

    private TreeSet<Integer> searchSudokuValues(Square[][] sudoku, int row, int col, final int DIMENSION) {
        var bounds = BoundaryCalc.calculateBounds(row, col, DIMENSION); // [start index for row, start index for col]
        var unavailableValues = searchGrid(sudoku, row, col, DIMENSION, bounds);
        unavailableValues.addAll(searchAxes(sudoku, row, col, unavailableValues));
        return unavailableValues;
    }

    private TreeSet<Integer> searchGrid(Square[][] sudoku, int row, int col, final int DIMENSION, int[] bounds) {
        var unavailableValues = new TreeSet<Integer>();
        for (int i = bounds[0]; i < bounds[0] + DIMENSION; i++) {
            for (int j = bounds[1]; j < bounds[1] + DIMENSION; j++) {
                if (!(i == row && j == col) && !(sudoku[i][j].getValue() == 0)) {
                    unavailableValues.add(sudoku[i][j].getValue());
                }
            }
        }
        return unavailableValues;
    }

    private TreeSet<Integer> searchAxes(Square[][] sudoku, int row, int col, TreeSet<Integer> unavailableValues) {
        unavailableValues.addAll(searchVerticalAxis(sudoku, row, col));
        unavailableValues.addAll(searchHorizontalAxis(sudoku, row, col));
        return unavailableValues;
    }

    private ArrayList<Integer> searchVerticalAxis(Square[][] sudoku, int row, int col) {
        var unavailableValues = new ArrayList<Integer>();
        for (int i = 0; i < sudoku.length; i++) {
            if (!(i == row) && !(sudoku[i][col].getValue() == 0)) {
                unavailableValues.add(sudoku[i][col].getValue());
            }
        }
        return unavailableValues;
    }

    private ArrayList<Integer> searchHorizontalAxis(Square[][] sudoku, int row, int col) {
        var unavailableValues = new ArrayList<Integer>();
        for (int i = 0; i < sudoku.length; i++) {
            if (!(i == col) && !(sudoku[row][i].getValue() == 0)) {
                unavailableValues.add(sudoku[row][i].getValue());
            }
        }
        return unavailableValues;
    }
}
