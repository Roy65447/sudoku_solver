package be.roy.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BoundaryCalc {

    public int[] calculateBounds(int row, int col, final int DIMENSION) {
        var bounds = new int[2];
        bounds[0] = calculateStartpoint(row, DIMENSION);
        bounds[1] = calculateStartpoint(col, DIMENSION);
        return bounds;
    }

    private int calculateStartpoint(int index, final int DIMENSION) {
        if (index < DIMENSION) {
            return 0;
        } else if (index >= DIMENSION * 2) {
            return DIMENSION * 2;
        } else {
            return DIMENSION;
        }
    }

}
