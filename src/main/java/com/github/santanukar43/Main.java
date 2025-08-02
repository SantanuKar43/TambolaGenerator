package com.github.santanukar43;

import com.github.santanukar43.command.GenerateCommand;
import com.github.santanukar43.core.Generator;
import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new GenerateCommand(new Generator()))
                .setExecutionExceptionHandler(new PrintExceptionMessageHandler())
                .execute(args);
        System.exit(exitCode);
    }
}