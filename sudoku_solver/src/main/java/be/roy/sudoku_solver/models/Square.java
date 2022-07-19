package be.roy.sudoku_solver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * represents 1 single square of sudoku
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Square {
    private int value;
}
