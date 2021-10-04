package be.roy.sudoku_solver.models;

import lombok.Getter;
import lombok.Setter;

/**
 * A class that contains the unsolved sudoku and the answer
 */
@Getter
@Setter
public class Sudoku {
    private final int DIMENSION = 3;
    private Square[][] unsolved;
    private Square[][] solved;
    private boolean isSolved;

    public Sudoku() {
        var sudokuDimension = DIMENSION * DIMENSION;
        unsolved = new Square[sudokuDimension][sudokuDimension];
        solved = new Square[sudokuDimension][sudokuDimension];
        isSolved = false;
    }

    public Sudoku(Square[][] unsolved) {
        this.unsolved = unsolved;
        this.solved = unsolved;
        isSolved = false;
    }

    // For console testing
    private String convertArrayToString(Square[][] solved) {
        var sudokuString = "";
        for (int i = 0; i < solved.length; i++) {
            if (i == 3 || i == 6) {
                sudokuString += "---------------------\n";
            }
            for (int j = 0; j < solved[i].length; j++) {
                if (j == 3 || j == 6) {
                    sudokuString += "| ";
                }
                sudokuString = sudokuString + (solved[i][j].getValue() == 0 ? "." : solved[i][j].getValue()) + " ";
            }
            sudokuString += "\n";
        }
        return sudokuString;
    }

    @Override
    public String toString() {
        return convertArrayToString(solved);
    }
}
