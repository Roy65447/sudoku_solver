package be.roy.models;

import java.util.ArrayList;

public class Square {
    private int value;
    private ArrayList<Integer> hints;

    public Square() {
        hints = new ArrayList<Integer>();
    }

    public void removeHint(int hintValue) {
        hints.removeIf(value -> value.equals(hintValue));
    }

    public void addHint(int hintValue) {
        if (hints.contains(hintValue)) {
            hints.add(hintValue);
        }
    }

    public int getValue() {
        return value;
    }

    public ArrayList<Integer> getHints() {
        return hints;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setHints(ArrayList<Integer> hints) {
        this.hints = hints;
    }
}
