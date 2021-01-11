package be.roy.sudoku_solver.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.roy.sudoku_solver.services.model.Sudoku;
import be.roy.sudoku_solver.services.model.SudokuSquareGrid;

@Service
public class SudokuSolverService {
  @Autowired
  private SudokuHintService sudokuHintService;

  public Sudoku solveSudoku(int dimension, HashMap<Integer, SudokuSquareGrid> unsolved) {
    var sudoku = new Sudoku(dimension);
    sudoku.setUnsolved(unsolved);
    sudoku.setSolved(unsolved);
    sudoku = sudokuHintService.fillHints(sudoku);
    return sudoku;
  }
}
