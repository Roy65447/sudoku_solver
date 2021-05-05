package be.roy.sudoku_solver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.roy.sudoku_solver.services.SudokuHintService;
import be.roy.sudoku_solver.services.SudokuSolverService;
import be.roy.sudoku_solver.services.model.*;

@SpringBootTest
class SudokuSolverApplicationTests {
	private HashMap<Integer, SudokuSquareGrid> unsolved;
	private Sudoku sudoku;
	@Autowired
	private SudokuSolverService sudokuSolverService;
	@Autowired
	private SudokuHintService sudokuHintService;

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
		sudoku = new Sudoku(unsolved);
	}

	@AfterEach
	void finish() {
		unsolved.clear();
	}

	@Test
	void allHintsAreFilled() {
		sudokuHintService.fillHints(sudoku);
		assertEquals(sudoku.getSolved().get(0).getSquares()[0].getHints(), new ArrayList<Integer>(Arrays.asList(3, 4, 5)));
		assertEquals(sudoku.getSolved().get(8).getSquares()[8].getHints(), new ArrayList<Integer>(Arrays.asList(2, 5, 9)));
		assertEquals(sudoku.getSolved().get(4).getSquares()[3].getHints(), new ArrayList<Integer>(Arrays.asList()));
	}

	@Test
	void sudokuIsSolved() {
		sudoku = sudokuSolverService.solveSudoku(unsolved);
		var solved = new HashMap<Integer, SudokuSquareGrid>();
		solved.put(0, new SudokuSquareGrid(new int[] { 4, 3, 5, 6, 8, 2, 1, 9, 7 }));
		solved.put(1, new SudokuSquareGrid(new int[] { 2, 6, 9, 5, 7, 1, 8, 3, 4 }));
		solved.put(2, new SudokuSquareGrid(new int[] { 7, 8, 1, 4, 9, 3, 5, 6, 2 }));
		solved.put(3, new SudokuSquareGrid(new int[] { 8, 2, 6, 3, 7, 4, 9, 5, 1 }));
		solved.put(4, new SudokuSquareGrid(new int[] { 1, 9, 5, 6, 8, 2, 7, 4, 3 }));
		solved.put(5, new SudokuSquareGrid(new int[] { 3, 4, 7, 9, 1, 5, 6, 2, 8 }));
		solved.put(6, new SudokuSquareGrid(new int[] { 5, 1, 9, 2, 4, 8, 7, 6, 3 }));
		solved.put(7, new SudokuSquareGrid(new int[] { 3, 2, 6, 9, 5, 7, 4, 1, 8 }));
		solved.put(8, new SudokuSquareGrid(new int[] { 8, 7, 4, 1, 3, 6, 2, 5, 9 }));
		var solvedValues = solved.values().stream().map(SudokuSquareGrid::getSquares).flatMap(Arrays::stream)
				.map(SudokuSquare::getValue).collect(Collectors.toList());
		var sudokuSolvedValues = sudoku.getSolved().values().stream().map(SudokuSquareGrid::getSquares)
				.flatMap(Arrays::stream).map(SudokuSquare::getValue).collect(Collectors.toList());
				System.out.println(sudoku);
		assertEquals(solvedValues, sudokuSolvedValues);
	}
}
