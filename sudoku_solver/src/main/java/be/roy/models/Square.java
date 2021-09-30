package be.roy.models;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Square {
    private int value;
    @Setter
    private ArrayList<Integer> hints;

    public Square() {
        hints = new ArrayList<Integer>();
    }

    public void removeHint(int hintValue) {
        hints.removeIf(value -> value.equals(hintValue));
    }

    public void addHint(int hintValue) {
        if (!hints.contains(hintValue)) {
            hints.add(hintValue);
        }
    }

    public void setValue(int value) {
        this.value = value;
        hints.clear();
    }
}
