package be.roy.models;

public class Sudoku {
    private final int DIMENSION = 3;
    private Square[][] unsolved;
    private Square[][] solved;

    public Sudoku() {
        unsolved = new Square[DIMENSION ^ 2][DIMENSION ^ 2];
        solved = new Square[DIMENSION ^ 2][DIMENSION ^ 2];
    }
}
