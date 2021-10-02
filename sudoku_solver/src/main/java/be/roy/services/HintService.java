package be.roy.services;

import java.util.TreeSet;
import org.springframework.stereotype.Service;
import be.roy.models.Square;
import lombok.var;

/**
 * Service dedicated to adding and removing hints to squares
 */
@Service
public class HintService {
    /**
     * Looks for every possible value possible in a square and puts them in the
     * hints arraylist
     * 
     * @param incompleteSudoku - the sudoku that needs to be solved
     * @param DIMENSION        - dimensions of the sudoku which is always 3x3
     * @return incompleteSudoku
     */
    Square[][] fillHints(Square[][] incompleteSudoku, int DIMENSION) {
        for (int i = 0; i < incompleteSudoku.length; i++) {
            for (int j = 0; j < incompleteSudoku[i].length; j++) {
                var square = incompleteSudoku[i][j];
                if (square.getValue() == 0) {
                    var unavailableValues = new TreeSet<Integer>();
                    
                    square = removeHints(unavailableValues, square);
                }
            }
        }
        return incompleteSudoku;
    }

    Square removeHints(TreeSet<Integer> unavailableValues, Square square) {
        for (Integer value : unavailableValues) {
            if (square.getHints().contains(value)) {
                square.removeHint(value);
            }
        }
        return square;
    }
}
