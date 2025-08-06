package com.github.santanukar43.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.StringUtils;

public class Generator {
    public TambolaGrid generateGrid(int rows, int cols, int numsPerRow) {
        if (rows > 10) {
            throw new IllegalArgumentException("More than 10 rows would break Tambola card rules");
        }

        String[][] grid = new String[rows][cols];
        int numsToDelete = cols - numsPerRow;
        int[] countOfNumsInCol = new int[cols];
        Arrays.fill(countOfNumsInCol, rows);

        // mark blanks first
        for (int i = 0; i < rows; i++) {
            int deleteCount = 0;
            while (deleteCount < numsToDelete) {
                int index = ThreadLocalRandom.current().nextInt(0, cols);
                if (!StringUtils.SPACE.equals(grid[i][index]) && countOfNumsInCol[index] > 1) {
                    grid[i][index] = StringUtils.SPACE;
                    countOfNumsInCol[index]--;
                    deleteCount++;
                }
            }
        }

        // generate and fill required numbers in a column-wise manner
        List<List<Integer>> availableNumbers = getAvailableNumbers(cols);
        for (int j = 0; j < cols; j++) {
            List<Integer> nums = getNumsForColumn(j, availableNumbers, countOfNumsInCol[j]);
            for (int i = 0; i < rows; i++) {
                if (!StringUtils.SPACE.equals(grid[i][j])) {
                    grid[i][j] = String.valueOf(nums.removeFirst());
                }
            }
        }
        return new TambolaGrid(grid);
    }

    private List<List<Integer>> getAvailableNumbers(int cols) {
        List<List<Integer>> available = new ArrayList<>();
        for (int colIndex = 0; colIndex < cols; colIndex++) {
            int start = colIndex * 10 + 1;
            int end = colIndex * 10 + 10;
            List<Integer> numbersInCol = new LinkedList<>();
            for (int num = start; num <= end; num++) {
                numbersInCol.add(num);
            }
            available.add(numbersInCol);
        }
        return available;
    }

    private List<Integer> getNumsForColumn(int colIndex, List<List<Integer>> availableNumbers, int count) {
        List<Integer> nums = new LinkedList<>();
        while (count-- > 0) {
            nums.add(getRandom(colIndex, availableNumbers));
        }
        Collections.sort(nums);
        return nums;
    }

    private int getRandom(int col, List<List<Integer>> availableNumbers) {
        if (availableNumbers.get(col).isEmpty()) {
            throw new IllegalStateException("No available numbers left for column " + col);
        }
        int idx = ThreadLocalRandom.current().nextInt(0, availableNumbers.get(col).size());
        return availableNumbers.get(col).remove(idx);
    }
}
