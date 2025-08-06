package com.github.santanukar43.command;

import com.github.santanukar43.core.Generator;
import com.github.santanukar43.core.TambolaGrid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine;

@CommandLine.Command(name = "tambola-gen", version = "1.0", mixinStandardHelpOptions = true, description = "This is a"
        + " simple CLI application to generate a Tambola card.")
public class GenerateCommand implements Runnable {
    private static final String COMMA = ",";
    private static final String LINE_BREAK = "\n";
    private static final String DEFAULT_ROWS = "3";
    private static final String DEFAULT_COLUMNS = "9";
    private static final String DEFAULT_NUMBERS_PER_ROW = "5";
    public static final String TAB = "\t";

    @CommandLine.Option(names = "-n",
            description = "Number of cards to print",
            defaultValue = "1",
            showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
    private int count;

    @CommandLine.Option(names = "--pretty", description = "Pretty print")
    private boolean pretty;

    @CommandLine.Option(names = "--csv", description = "CSV print")
    private boolean csv;

    @CommandLine.Option(names = "--tsv", description = "TSV print")
    private boolean tsv;

    @CommandLine.Option(names = {"-r",
            "--rows"},
            description = "Number of rows",
            defaultValue = DEFAULT_ROWS,
            hideParamSyntax = true,
            showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
    private int rows;

    @CommandLine.Option(names = {"-c",
            "--cols"},
            description = "Number of columns",
            defaultValue = DEFAULT_COLUMNS,
            hideParamSyntax = true,
            showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
    private int cols;

    @CommandLine.Option(names = "--numsPerRow",
            description = "Numbers per row",
            defaultValue =
            DEFAULT_NUMBERS_PER_ROW,
            hideParamSyntax = true,
            showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
    private int numsPerRow;

    private final Generator generator;

    public GenerateCommand(Generator generator) {
        this.generator = generator;
    }

    @Override
    public void run() {
        Set<TambolaGrid> generatedGrids = new HashSet<>();
        while (count-- > 0) {
            TambolaGrid grid = generator.generateGrid(rows, cols, numsPerRow);
            if (generatedGrids.contains(grid)) {
                continue;
            }
            generatedGrids.add(grid);

            if (pretty) {
                prettyPrint(grid);
            } else if (csv) {
                tabularPrint(grid, COMMA);
            } else if (tsv) {
                tabularPrint(grid, TAB);
            } else {
                basicPrint(grid);
            }
            System.out.println();
        }
    }

    public void basicPrint(TambolaGrid grid) {
        System.out.println("[" +
                Arrays.stream(grid.getGrid()).map(Arrays::toString).collect(Collectors.joining(",\n"))
                + "]");
    }

    public void prettyPrint(TambolaGrid grid) {
        for (String[] row : grid.getGrid()) {
            for (String cell : row) {
                System.out.printf("%-4s", StringUtils.isBlank(cell) ? "." : cell);
            }
            System.out.println();
        }
    }

    public void tabularPrint(TambolaGrid grid, String separator) {
        for (String[] row : grid.getGrid()) {
            for (int i = 0; i < row.length; i++) {
                System.out.printf("%s", i == row.length - 1 ? row[i] + LINE_BREAK : row[i] + separator);
            }
        }
    }
}
