package net.ttiimm.lox;

public class Reporter {

    public static boolean hadError = false;

    public static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where, String message) {
        System.err.println(STR."[line \{line}] Error\{where}: \{message}");
        hadError = true;
    }
}