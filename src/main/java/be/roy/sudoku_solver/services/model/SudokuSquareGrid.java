package be.roy.sudoku_solver.services.model;

import java.util.List;

import lombok.Getter;

@Getter
public class SudokuSquareGrid {
  private final SudokuSquare[] squares = new SudokuSquare[9];

  public SudokuSquareGrid() {
    for (int i = 0; i < 9; i++) {
      this.squares[i] = new SudokuSquare();
    }
  }

  public SudokuSquareGrid(int[] squares) {
    this.setSquares(squares);
  }
// public int[] getValues(){
  
// }
  public void setSquares(int[] squareValues) {
    for (int i = 0; i < squareValues.length; i++) {
      if (squareValues[i] <= 9) {
        this.squares[i] = new SudokuSquare();
        this.squares[i].setValue(squareValues[i]);
      }
    }
  }

  public void setSquare(SudokuSquare square, int index) {
    this.squares[index] = square;
  }

  public void setHints(int squareId, List<Integer> hints) {
    this.squares[squareId].setHints(hints);
  }
}
