package be.roy.sudoku_solver.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.roy.sudoku_solver.services.model.Sudoku;
import be.roy.sudoku_solver.services.model.SudokuSquareGrid;
import lombok.var;

@Service
public class SudokuSolverService {
  @Autowired
  private SudokuHintService sudokuHintService;

  public Sudoku solveSudoku(HashMap<Integer, SudokuSquareGrid> unsolved) {
    var sudoku = new Sudoku(unsolved);
    sudoku = fillValues(sudokuHintService.fillHints(sudoku));
    return sudoku;
  }

  private Sudoku fillValues(Sudoku sudoku) {
    return replaceSingleHints(sudoku);
  }

  private Sudoku replaceSingleHints(Sudoku sudoku) {
    var solved = sudoku.getSolved();
    for (int gridId = 0; gridId < solved.size(); gridId++) {
      var grid = solved.get(gridId);
      for (int squareId = 0; squareId < grid.getSquares().length; squareId++) {
        var square = grid.getSquares()[squareId];
        if (square.getValue() == 0 && square.getHints().size() == 1) {
          var squareValue = square.getHints().get(0);
          grid.getSquares()[squareId].setValue(squareValue);
          solved.replace(gridId, grid);
          solved = removeValueFromOtherHints(solved, gridId, squareId, sudoku.getDimension(), squareValue);
        }
      }
    }
    sudoku.setSolved(solved);
    return sudoku;
  }

  private HashMap<Integer, SudokuSquareGrid> removeValueFromOtherHints(HashMap<Integer, SudokuSquareGrid> solved,
      int gridId, int squareId, int dimension, int squareValue) {
    solved = removeValueFromGridHints(solved, squareValue, gridId, dimension);
    solved = removeValueFromAxesHints(solved, gridId, squareId, dimension, squareValue);
    return solved;
  }

  private HashMap<Integer, SudokuSquareGrid> removeValueFromGridHints(HashMap<Integer, SudokuSquareGrid> solved,
      int squareValue, int gridId, int dimension) {
    var grid = solved.get(gridId);
    for (var squareId = 0; squareId < grid.getSquares().length; squareId++) {
      var square = grid.getSquares()[squareId];
      if (square.getValue() == 0 && square.getHints().contains(squareValue)) {
        square.getHints().remove(Integer.valueOf(squareValue));
        grid.setSquare(square, squareId);
        solved.replace(gridId, grid);
        if (square.getHints().size() == 1) {
          square.setValue(square.getHints().get(0));
          grid.setSquare(square, squareId);
          solved.replace(gridId, grid);
          solved = removeValueFromOtherHints(solved, gridId, squareId, dimension, square.getValue());
        }
      }
    }
    return solved;
  }

  private HashMap<Integer, SudokuSquareGrid> removeValueFromAxesHints(HashMap<Integer, SudokuSquareGrid> solved,
      int gridId, int squareId, int dimension, int squareValue) {
    solved = removeValueFromVerticalAxisHints(solved, gridId, squareId, dimension, squareValue);
    solved = removeValueFromHorizontalAxisHints(solved, gridId, squareId, dimension, squareValue);
    return solved;
  }

  private HashMap<Integer, SudokuSquareGrid> removeValueFromVerticalAxisHints(HashMap<Integer, SudokuSquareGrid> solved,
      int gridId, int squareId, int dimension, int squareValue) {
    var startId = sudokuHintService.goToTop(gridId, dimension);
    for (int i = startId; i < solved.size(); i += dimension) {
      if (gridId != i) {
        var gridTop = sudokuHintService.goToTop(squareId, dimension);
        var grid = solved.get(i);
        var squares = grid.getSquares();
        for (int j = gridTop; j < squares.length; j += dimension) {
          var square = squares[j];
          if (square.getValue() == 0 && square.getHints().contains(squareValue)) {
            square.getHints().remove(Integer.valueOf(squareValue));
            grid.setSquare(square, j);
            solved.replace(i, grid);
            if (square.getHints().size() == 1) {
              square.setValue(square.getHints().get(0));
              grid.setSquare(square, j);
              solved.replace(i, grid);
              solved = removeValueFromOtherHints(solved, i, j, dimension, square.getValue());
            }
          }
        }
      }
    }
    return solved;
  }

  private HashMap<Integer, SudokuSquareGrid> removeValueFromHorizontalAxisHints(
      HashMap<Integer, SudokuSquareGrid> solved, int gridId, int squareId, int dimension, int squareValue) {
    var startId = sudokuHintService.goToStart(gridId, dimension);
    for (int i = startId; i < startId + dimension; i++) {
      if (gridId != i) {
        var gridStart = sudokuHintService.goToStart(squareId, dimension);
        var grid = solved.get(i);
        var squares = grid.getSquares();
        for (int j = gridStart; j < gridStart + dimension; j++) {
          var square = squares[j];
          if (square.getValue() == 0 && square.getHints().contains(squareValue)) {
            square.getHints().remove(Integer.valueOf(squareValue));
            grid.setSquare(square, j);
            solved.replace(i, grid);
            if (square.getHints().size() == 1) {
              square.setValue(square.getHints().get(0));
              grid.setSquare(square, j);
              solved.replace(i, grid);
              solved = removeValueFromOtherHints(solved, i, j, dimension, square.getValue());
            }
          }
        }
      }
    }
    return solved;
  }
}
