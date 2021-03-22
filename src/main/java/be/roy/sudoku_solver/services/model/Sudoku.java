package be.roy.sudoku_solver.services.model;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sudoku {
  private final int dimension = 3;
  private HashMap<Integer, SudokuSquareGrid> unsolved;
  private HashMap<Integer, SudokuSquareGrid> solved;

  public Sudoku() {
    this.unsolved = new HashMap<Integer, SudokuSquareGrid>();
    this.solved = new HashMap<Integer, SudokuSquareGrid>();
  }

  public Sudoku(HashMap<Integer, SudokuSquareGrid> unsolved) {
    this.unsolved = unsolved;
    this.solved = unsolved;
  }

  private String[] convertToStringArray(HashMap<Integer, SudokuSquareGrid> solved) {
    String[] sudoku = new String[(dimension * dimension) + (dimension - 1)];
    for (int i = 0; i < sudoku.length; i++) {
      sudoku[i] = "[";
    }
    for (var gridId = 0; gridId < this.solved.size(); gridId++) {
      var indexCounter = 0;
      if (gridId >= dimension * 2) {
        indexCounter = dimension * 2 + 2;
      } else if (gridId >= dimension && gridId < dimension * 2) {
        indexCounter = dimension + 1;
      }
      for (var squareId = 0; squareId < this.solved.get(gridId).getSquares().length; squareId++) {
        if (gridId > 0 && gridId % 3 != 0 & squareId % 3 == 0) {
          sudoku[indexCounter] = sudoku[indexCounter] + "  |";
        }
        if (this.solved.get(gridId).getSquares()[squareId].getValue() == 0) {
          sudoku[indexCounter] = sudoku[indexCounter] + "  -";
        } else {
          sudoku[indexCounter] = sudoku[indexCounter] + "  "
              + this.solved.get(gridId).getSquares()[squareId].getValue();
        }
        if (squareId % 3 == 2 && squareId < (this.solved.get(gridId).getSquares().length - 2)) {
          indexCounter++;
        }
      }
    }
    for (int i = 0; i < sudoku.length; i++) {
      sudoku[i] += " ]";
    }
    return sudoku;
  }

  @Override
  public String toString() {
    var sudoku = convertToStringArray(this.solved);
    String sudokuString = "";
    for (String string : sudoku) {
      sudokuString = String.format("%s%n%s", sudokuString, string);
    }
    return sudokuString;
  }
}