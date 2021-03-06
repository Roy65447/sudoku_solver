package be.roy.sudoku_solver.services.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a single square
 */
@Getter
public class SudokuSquare {
  private int value;
  /**
   * Every indiviual square has a list of hints that can contain numbers that
   * range from 1-9 Hints are possible numbers that a user might want to remember
   * but that don't count as a solution
   */
  @Setter
  private List<Integer> hints;

  public SudokuSquare(){
    this.value = 0;
    this.hints = new ArrayList<>();
  }

  public void setValue(int value){
    this.value = value;
    this.hints = Collections.emptyList();
  }
}
