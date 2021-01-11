package be.roy.sudoku_solver.services.model;

import java.util.List;

import lombok.Getter;

@Getter
public class SudokuSquareGrid {
  private final SudokuSquare[] squares = new SudokuSquare[9];

  public SudokuSquareGrid() {
    for(int i = 0; i < 9; i++){
      this.squares[i] = new SudokuSquare();
    }
  }

  public SudokuSquareGrid(int[] squares) {
    this.setSquares(squares);
  }

  /**
   * Set the value of square in the 3x3 grid
   * 
   * @param squareId Ranges from 0-8; representing the 9 squares in the 3x3 grid
   *                 [0,1,2] [3,4,5] [6,7,8]
   * @param value    represents the value that goes in a certain square
   */
  public void setSquareValue(int squareId, int value) {
    if (squareId <= 9 && value <= 9) {
      this.squares[squareId].setValue(value);
    }
  }

  public void setSquares(int[] squareValues) {
    for (int i = 0; i < squareValues.length; i++) {
      if (squareValues[i] <= 9) {
        this.squares[i] = new SudokuSquare();
        this.squares[i].setValue(squareValues[i]);
      }
    }
  }

  public void setHints(int squareId, List<Integer> hints) {
    this.squares[squareId].setHints(hints);
  }
}
