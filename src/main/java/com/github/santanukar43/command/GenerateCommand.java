package com.github.santanukar43.command;

import com.github.santanukar43.core.Generator;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine;

@CommandLine.Command(name = "tambola-gen",
        version = "1.0",
        mixinStandardHelpOptions = true,
        description = "This is a simple CLI application to generate a Tambola card.")
public class GenerateCommand implements Runnable {
    private static final String COMMA = ",";
    private static final String LINE_BREAK = "\n";
    private static final String DEFAULT_ROWS = "3";
    private static final String DEFAULT_COLUMNS = "9";
    private static final String DEFAULT_NUMBERS_PER_ROW = "5";

    @CommandLine.Option(names = "--pretty", description = "Pretty print")
    private boolean pretty;

    @CommandLine.Option(names = "--csv", description = "CSV print")
    private boolean csv;

    @CommandLine.Option(names = {"-r", "--rows"}, description = "Number of rows, defaults to "
            + DEFAULT_ROWS, defaultValue = DEFAULT_ROWS, hideParamSyntax = true)
    private int rows;

    @CommandLine.Option(names = {"-c", "--cols"}, description = "Number of columns, defaults to "
            + DEFAULT_COLUMNS, defaultValue = DEFAULT_COLUMNS, hideParamSyntax = true)
    private int cols;

    @CommandLine.Option(names = "--numsPerRow", description = "Numbers per row, defaults to "
            + DEFAULT_NUMBERS_PER_ROW, defaultValue = DEFAULT_NUMBERS_PER_ROW, hideParamSyntax = true)
    private int numsPerRow;

    private final Generator generator;

    public GenerateCommand(Generator generator) {
        this.generator = generator;
    }

    @Override
    public void run() {
        String[][] grid = generator.generateGrid(rows, cols, numsPerRow);
        if (pretty) {
            prettyPrint(grid);
        } else if (csv) {
            csvPrint(grid);
        } else {
            basicPrint(grid);
        }
    }

    public void basicPrint(String[][] grid) {
        for (String[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void prettyPrint(String[][] grid) {
        for (String[] row : grid) {
            for (String cell : row) {
                System.out.printf("%-4s", StringUtils.isBlank(cell) ? "." : cell);
            }
            System.out.println();
        }
    }

    public void csvPrint(String[][] grid) {
        for (String[] row : grid) {
            for (int i = 0; i < row.length; i++) {
                System.out.printf("%s", i == row.length - 1 ? row[i] + LINE_BREAK : row[i] + COMMA);
            }
        }
    }
}
