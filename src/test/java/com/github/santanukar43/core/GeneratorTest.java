package com.github.santanukar43.core;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

class GeneratorTest {
    private final Generator generator = new Generator();
    @Test
    public void sanityTest () {
        for (int i = 0; i < 5000; i++) {
            assertTrue(isValid(generator.generateGrid(3, 9, 5)));
        }
    }

    private boolean isValid(String[][] grid) {
        for (String[] row : grid) {
            if (!isValidRow(row)) {
                System.out.println("Invalid row\n" + getString(grid));
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (!isValidColumn(i, grid)) {
                System.out.println("Invalid column\n" + getString(grid));
                return false;
            }
        }
        return true;
    }

    private static String getString(String[][] grid) {
        return Arrays.stream(grid).map(Arrays::toString).collect(Collectors.joining("\n"));
    }

    private boolean isValidColumn(int colId, String[][] grid) {
        List<Integer> numsInCol = new ArrayList<>();
        int start = colId * 10 + 1, end = colId * 10 + 10;
        for (int i = 0; i < 3; i++) {
            if (StringUtils.isNotBlank(grid[i][colId])) {
                int num = Integer.parseInt(grid[i][colId]);
                if (!inRange(start, end, num)) {
                    System.out.println(num + " not in range: " + start + ", " + end);
                    return false;
                }
                numsInCol.add(num);
            }
        }
        return !numsInCol.isEmpty() && isSorted(numsInCol);
    }

    private boolean isSorted(List<Integer> numsInCol) {
        for (int i = 1; i < numsInCol.size(); i++) {
            if (numsInCol.get(i - 1) >= numsInCol.get(i)) return false;
        }
        return true;
    }

    private static boolean inRange(int start, int end, int num) {
        return num >= start && num <= end;
    }

    private boolean isValidRow(String[] row) {
        int nonEmptyCount = 0;
        for (int i = 0; i < row.length; i++) {
            if (StringUtils.isNotBlank(row[i])) {
                nonEmptyCount++;
            }
        }
        return nonEmptyCount == 5;
    }
}