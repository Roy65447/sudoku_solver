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
}
