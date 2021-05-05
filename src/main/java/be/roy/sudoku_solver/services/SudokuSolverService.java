package be.roy.sudoku_solver.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.roy.sudoku_solver.services.model.Sudoku;
import be.roy.sudoku_solver.services.model.SudokuSquare;
import be.roy.sudoku_solver.services.model.SudokuSquareGrid;
import lombok.var;

@Service
public class SudokuSolverService {
  @Autowired
  private SudokuHintService sudokuHintService;

  public Sudoku solveSudoku(HashMap<Integer, SudokuSquareGrid> unsolved) {
    var sudoku = new Sudoku(unsolved);
    sudoku = replaceSingleHints(sudokuHintService.fillHints(sudoku));
    return sudoku;
  }

  private HashMap<Integer, SudokuSquareGrid> fillSquare(SudokuSquare square, SudokuSquareGrid grid, int squareId,
      int gridId, int dimension, HashMap<Integer, SudokuSquareGrid> solved) {
    square.setValue(square.getHints().get(0));
    grid.setSquare(square, squareId);
    solved.replace(gridId, grid);
    solved = removeValueFromOtherHints(solved, gridId, squareId, dimension, square.getValue());
    return solved;
  }

  private Sudoku replaceSingleHints(Sudoku sudoku) {
    var solved = sudoku.getSolved();
    for (int gridId = 0; gridId < solved.size(); gridId++) {
      var grid = solved.get(gridId);
      for (int squareId = 0; squareId < grid.getSquares().length; squareId++) {
        var square = grid.getSquares()[squareId];
        if (square.getValue() == 0 && square.getHints().size() == 1)
          solved = fillSquare(square, grid, squareId, gridId, sudoku.getDimension(), solved);
      }
    }
    sudoku.setSolved(solved);
    return sudoku;
  }

  private HashMap<Integer, SudokuSquareGrid> removeValueFromOtherHints(HashMap<Integer, SudokuSquareGrid> solved,
      int gridId, int squareId, int dimension, int squareValue) {
    solved = removeValueFromGridHints(solved, squareValue, gridId, dimension);
    solved = removeValueFromAxisHints(solved, gridId, squareId, dimension, squareValue);
    return solved;
  }

  private HashMap<Integer, SudokuSquareGrid> removeValueFromHints(SudokuSquare square, int squareValue,
      SudokuSquareGrid grid, int squareId, int gridId, HashMap<Integer, SudokuSquareGrid> solved) {
    square.getHints().remove(Integer.valueOf(squareValue));
    grid.setSquare(square, squareId);
    solved.replace(gridId, grid);
    return solved;
  }

  private HashMap<Integer, SudokuSquareGrid> removeValueFromGridHints(HashMap<Integer, SudokuSquareGrid> solved,
      int squareValue, int gridId, int dimension) {
    var grid = solved.get(gridId);
    for (var squareId = 0; squareId < grid.getSquares().length; squareId++) {
      var square = grid.getSquares()[squareId];
      if (square.getValue() == 0 && square.getHints().contains(squareValue)) {
        solved = removeValueFromHints(square, squareValue, grid, squareId, gridId, solved);
        if (square.getHints().size() == 1)
          solved = fillSquare(square, grid, squareId, gridId, dimension, solved);
      }
    }
    return solved;
  }

  private HashMap<Integer, SudokuSquareGrid> removeValueFromAxisHints(HashMap<Integer, SudokuSquareGrid> solved,
      int gridId, int squareId, int dimension, int squareValue) {
    boolean horizontal = true;
    for (int count = 0; count <= 1; count++) {
      var startId = horizontal ? sudokuHintService.goToStart(gridId, dimension)
          : sudokuHintService.goToTop(gridId, dimension);
      for (int i = startId; horizontal ? i < startId + dimension
          : i < solved.size(); i = horizontal ? i + 1 : i + dimension) {
        if (gridId != i) {
          /*
           * Gridstartpoint for the horizontal axis is the grid most left from our current
           * position. For the vertical axis, this is the grid most upper grid from our
           * current position
           */
          var gridStartPoint = horizontal ? sudokuHintService.goToStart(squareId, dimension)
              : sudokuHintService.goToTop(squareId, dimension);
          var grid = solved.get(i);
          var squares = grid.getSquares();
          for (int j = gridStartPoint; horizontal ? j < gridStartPoint + dimension
              : j < squares.length; j = horizontal ? j + 1 : j + dimension) {
            var square = squares[j];
            if (square.getValue() == 0 && square.getHints().contains(squareValue)) {
              solved = removeValueFromHints(square, squareValue, grid, j, i, solved);
              if (square.getHints().size() == 1)
                solved = fillSquare(square, grid, j, i, dimension, solved);
            }
          }
        }
      }
      horizontal = false;
    }
    return solved;
  }
}
