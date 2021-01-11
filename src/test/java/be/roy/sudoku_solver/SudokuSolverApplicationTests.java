package be.roy.sudoku_solver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.roy.sudoku_solver.services.SudokuSolverService;
import be.roy.sudoku_solver.services.model.*;

@SpringBootTest
class SudokuSolverApplicationTests {
	private final int dimension = 3;
	private HashMap<Integer, SudokuSquareGrid> unsolved;
	@Autowired
	private SudokuSolverService sudokuSolverService;

	@BeforeEach
	void setup() {
		unsolved = new HashMap<>();
		unsolved.put(0, new SudokuSquareGrid(new int[] { 0, 0, 0, 6, 8, 0, 1, 9, 0 }));
		unsolved.put(1, new SudokuSquareGrid(new int[] { 2, 6, 0, 0, 7, 0, 0, 0, 4 }));
		unsolved.put(2, new SudokuSquareGrid(new int[] { 7, 0, 1, 0, 9, 0, 5, 0, 0 }));
		unsolved.put(3, new SudokuSquareGrid(new int[] { 8, 2, 0, 0, 0, 4, 0, 5, 0 }));
		unsolved.put(4, new SudokuSquareGrid(new int[] { 1, 0, 0, 6, 0, 2, 0, 0, 3 }));
		unsolved.put(5, new SudokuSquareGrid(new int[] { 0, 4, 0, 9, 0, 0, 0, 2, 8 }));
		unsolved.put(6, new SudokuSquareGrid(new int[] { 0, 0, 9, 0, 4, 0, 7, 0, 3 }));
		unsolved.put(7, new SudokuSquareGrid(new int[] { 3, 0, 0, 0, 5, 0, 0, 1, 8 }));
		unsolved.put(8, new SudokuSquareGrid(new int[] { 0, 7, 4, 0, 3, 6, 0, 0, 0 }));
	}

	@AfterEach
	void finish() {
		unsolved.clear();
	}

	@Test
	void allHintsAreFilled() {
		Sudoku sudoku = sudokuSolverService.solveSudoku(dimension, unsolved);
		assertEquals(sudoku.getSolved().get(0).getSquares()[0].getHints(), new ArrayList<Integer>(Arrays.asList(3, 4, 5)));
		assertEquals(sudoku.getSolved().get(8).getSquares()[8].getHints(), new ArrayList<Integer>(Arrays.asList(2, 5, 9)));
		assertEquals(sudoku.getSolved().get(4).getSquares()[3].getHints(), new ArrayList<Integer>(Arrays.asList()));
	}
}
