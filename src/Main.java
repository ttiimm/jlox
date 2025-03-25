// Lox.java

import net.ttiimm.lox.Scanner;
import net.ttiimm.lox.Token;
import net.ttiimm.lox.Reporter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

void main(String[] args) throws IOException {
    if (args.length > 1) {
        System.out.println("Usage: jlox [script]");
        System.exit(64);
    } else if (args.length == 1) {
        runFile(args[0]);
    } else {
        runPrompt();
    }
}

private void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));

    if (Reporter.hadError) {
        System.exit(65);
    }
}

private void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);

    while (true) {
        System.out.print("> ");
        String line = reader.readLine();
        if (line == null) break;
        run(line);
        Reporter.hadError = false;
    }
}

private static void run(String source) {
    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();

    // For now, print the tokens
    for (Token token : tokens) {
        System.out.println(token);
    }
}
