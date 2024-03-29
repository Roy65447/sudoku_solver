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
    private boolean sudokuSolved;

    public Sudoku() {
        var sudokuDimension = DIMENSION * DIMENSION;
        unsolved = new Square[sudokuDimension][sudokuDimension];
        solved = new Square[sudokuDimension][sudokuDimension];
        sudokuSolved = false;
    }

    public Sudoku(int[][] unsolved) {
        Square[][] convertedUnsolved = convertIntArrayToSquareArray(unsolved);
        this.unsolved = convertedUnsolved;
        this.solved = convertedUnsolved;
        sudokuSolved = false;
    }

    private Square[][] convertIntArrayToSquareArray(int[][] unsolved) {
        Square[][] convertedUnsolved = new Square[9][9];
        for (int i = 0; i < unsolved.length; i++) {
            for (int j = 0; j < unsolved[i].length; j++) {
                convertedUnsolved[i][j] = new Square(unsolved[i][j]);
            }
        }
        return convertedUnsolved;
    }
    
    // For console testing
    private String convertArrayToString(Square[][] solved) {
        var sudokuString = "";
        for (int i = 0; i < solved.length; i++) {
            if (i % DIMENSION == 0 && i != 0) {
                sudokuString += "---------------------\n";
            }
            for (int j = 0; j < solved[i].length; j++) {
                if (j % DIMENSION == 0 && j != 0) {
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
