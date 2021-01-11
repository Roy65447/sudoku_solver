package be.roy.sudoku_solver.services.model;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sudoku {
  private int dimension;
  private HashMap<Integer, SudokuSquareGrid> unsolved;
  private HashMap<Integer, SudokuSquareGrid> solved;

  public Sudoku(int dimension) {
    this.dimension = dimension;
    this.unsolved = new HashMap<Integer, SudokuSquareGrid>();
    this.solved = new HashMap<Integer, SudokuSquareGrid>();
  }
}