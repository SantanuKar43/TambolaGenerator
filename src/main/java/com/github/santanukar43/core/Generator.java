package com.github.santanukar43.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.StringUtils;

public class Generator {
    public String[][] generateGrid(int rows, int cols, int numsPerRow) {
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

        // generate and fill only required numbers in a column-wise manner
        Set<Integer> used = new HashSet<>();
        for (int j = 0; j < cols; j++) {
            List<Integer> nums = getNumsForColumn(j, used, countOfNumsInCol[j]);
            for (int i = 0; i < rows; i++) {
                if (!StringUtils.SPACE.equals(grid[i][j])) {
                    grid[i][j] = String.valueOf(nums.removeFirst());
                }
            }
        }
        return grid;
    }

    private List<Integer> getNumsForColumn(int colIndex, Set<Integer> used, int count) {
        int start = colIndex * 10 + 1;
        int end = start + 10;
        List<Integer> nums = new LinkedList<>();
        while (count-- > 0) {
            nums.add(getRandom(start, end, used));
        }
        Collections.sort(nums);
        return nums;
    }

    private int getRandom(int start, int end, Set<Integer> used) {
        int num = ThreadLocalRandom.current().nextInt(start, end + 1);
        while (used.contains(num)) {
            num = ThreadLocalRandom.current().nextInt(start, end + 1);
        }
        used.add(num);
        return num;
    }
}
