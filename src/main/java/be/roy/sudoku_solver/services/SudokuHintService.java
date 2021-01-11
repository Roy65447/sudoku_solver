package be.roy.sudoku_solver.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import be.roy.sudoku_solver.services.model.*;

@Service
public class SudokuHintService {
  public Sudoku fillHints(Sudoku sudoku) {
    var solved = sudoku.getSolved();
    for (int gridId = 0; gridId < solved.size(); gridId++) {
      var grid = solved.get(gridId);
      for (int squareId = 0; squareId < grid.getSquares().length; squareId++) {
        if (grid.getSquares()[squareId].getValue() == 0) {
          var hints = new ArrayList<Integer>();
          // fill hints with all possible values
          for (int i = 1; i < 10; i++) {
            hints.add(i);
          }
          // remove occuring ones
          hints = searchGrid(hints, grid);
          hints = searchAxes(gridId, squareId, sudoku.getDimension(), hints, solved);
          grid.setHints(squareId, hints);
        }
      }
      solved.replace(gridId, grid);
    }
    sudoku.setSolved(solved);
    return sudoku;
  }

  private ArrayList<Integer> searchGrid(ArrayList<Integer> hints, SudokuSquareGrid grid) {
    for (var square : grid.getSquares()) {
      if (square.getValue() != 0) {
        hints.remove(Integer.valueOf(square.getValue()));
      }
    }
    return hints;
  }

  private ArrayList<Integer> searchAxes(int gridId, int squareId, int dimension, ArrayList<Integer> hints,
      HashMap<Integer, SudokuSquareGrid> solved) {
    hints = searchVertically(gridId, squareId, dimension, hints, solved);
    hints = searchHorizontally(gridId, squareId, dimension, hints, solved);
    return hints;
  }

  private ArrayList<Integer> searchVertically(int gridId, int squareId, int dimension, ArrayList<Integer> hints,
      HashMap<Integer, SudokuSquareGrid> solved) {
    var startId = goToTop(gridId, dimension);
    for (int i = startId; i < solved.size(); i += dimension) {
      if (gridId != startId) { // don't search in current grid
        var gridTop = goToTop(squareId, 3);
        var squares = solved.get(i).getSquares();
        for (int j = gridTop; j < squares.length; j += 3) {
          if (squares[j].getValue() != 0 && hints.contains(squares[j].getValue())) {
            hints.remove(Integer.valueOf(squares[j].getValue()));
          }
        }
      }
    }
    return hints;
  }

  private int goToTop(int index, int dimension) {
    while (index > dimension) {
      index -= dimension;
    }
    return index;
  }

  private ArrayList<Integer> searchHorizontally(int gridId, int squareId, int dimension, ArrayList<Integer> hints,
      HashMap<Integer, SudokuSquareGrid> solved) {
    var startId = goToStart(gridId, dimension);
    for (int i = startId; i < startId + dimension; i++) {
      if (gridId != i) { // don't search in current grid
        var gridStart = goToStart(squareId, 3);
        var squares = solved.get(i).getSquares();
        for (int j = gridStart; j < gridStart + 3; j++) {
          if (squares[j].getValue() != 0 && hints.contains(squares[j].getValue())) {
            hints.remove(Integer.valueOf(squares[j].getValue()));
          }
        }
      }
    }
    return hints;
  }

  private int goToStart(int index, int dimension) {
    while (index % dimension != 0) {
      index--;
    }
    return index;
  }
}
