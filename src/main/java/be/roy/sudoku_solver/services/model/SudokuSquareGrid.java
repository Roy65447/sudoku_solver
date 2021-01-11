package be.roy.sudoku_solver.services.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SudokuSquareGrid {
  private final SudokuSquare[] squares = new SudokuSquare[9];
  
  /**
   * Set the value of square in the 3x3 grid
   * @param squareId Ranges from 0-8; representing the 9 squares in the 3x3 grid
   *                 [0,1,2]
   *                 [3,4,5] 
   *                 [6,7,8]
   * @param value represents the value that goes in a certain square
   */
  public void setSquareValue(int squareId, int value) {
    if (squareId > 9 && value > 9) {
      this.squares[squareId].setValue(value);
    }
  }

  public void setHints(int squareId, List<Integer> hints){
    this.squares[squareId].setHints(hints);
  }
}
