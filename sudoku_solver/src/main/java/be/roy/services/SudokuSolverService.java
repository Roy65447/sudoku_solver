package be.roy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.roy.models.Square;
import be.roy.models.Sudoku;

/**
 * Service that solves an unsolved sudoku
 */
@Service
public class SudokuSolverService {
    @Autowired
    private HintService hintService;

    public Sudoku solveSudoku(Sudoku sudoku) {
        sudoku.setSolved(replaceSingleHints(sudoku));
        return sudoku;
    }

    private Square[][] replaceSingleHints(Sudoku sudoku) {
        var incompleteSudoku = sudoku.getUnsolved();
        incompleteSudoku = hintService.fillHints(incompleteSudoku, sudoku.getDIMENSION());
        return incompleteSudoku;
    }
}
