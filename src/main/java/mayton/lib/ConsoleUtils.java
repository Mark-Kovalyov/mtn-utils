package mayton.lib;

import java.io.PrintStream;

public class ConsoleUtils {

    public static void println(String s) {
        System.out.println(s);
    }

    public static void print(String s) {
        System.out.print(s);
    }

    public static PrintStream printf(String format, Object ...args) {
        return System.out.printf(format, args);
    }

}
