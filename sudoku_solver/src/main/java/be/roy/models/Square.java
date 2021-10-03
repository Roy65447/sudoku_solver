package be.roy.models;

import java.util.ArrayList;
import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;

/**
 * represents 1 single square of sudoku
 */
@Getter
public class Square {
    private int value;
    @Setter
    private ArrayList<Integer> hints;

    public Square() {
        hints = new ArrayList<Integer>();
    }

    public Square(int value) {
        this.value = value;
        if (value == 0) {
            hints = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        } else {
            hints = new ArrayList<Integer>();
        }
    }

    public void removeHint(int hintValue) {
        hints.removeIf(value -> value.equals(hintValue));
    }

    public void setValue(int value) {
        this.value = value;
        if (!(value == 0)) {
            hints.clear();
        }
    }
}
