package com.github.santanukar43.core;

import java.util.Arrays;
import java.util.Objects;

public class TambolaGrid {
    private final String[][] grid;

    public TambolaGrid(String[][] grid) {
        this.grid = grid;
    }

    public String[][] getGrid() {
        return grid;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TambolaGrid that)) {
            return false;
        }
        return Objects.deepEquals(grid, that.grid);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(grid);
    }
}
