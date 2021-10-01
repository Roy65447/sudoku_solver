package be.roy.models;

import lombok.Getter;

@Getter
public class Sudoku {
    private final int DIMENSION = 3;
    private Square[][] unsolved;
    private Square[][] solved;

    public Sudoku() {
        unsolved = new Square[DIMENSION ^ 2][DIMENSION ^ 2];
        solved = new Square[DIMENSION ^ 2][DIMENSION ^ 2];
    }

    public Sudoku(Square[][] unsolved) {
        this.unsolved = unsolved;
        this.solved = unsolved;
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
