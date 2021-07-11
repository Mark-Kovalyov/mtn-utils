package mayton.lib.html;

import org.apache.commons.lang3.StringUtils;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Monospaced HTML5 text-like colored report
 */
public class ColoredHtmlReport implements AutoCloseable {

    private static String htmlEscape(String s) {
        return StringUtils.replaceEach(s,
                new String[]{"&",     "\"",     "<",    ">",    "'" },
                new String[]{"&amp;", "&quot;", "&lt;", "&gt;", "&apos;"});
    }

    private PrintWriter printWriter;

    public ColoredHtmlReport(OutputStream outputStream) {
        printWriter = new PrintWriter(outputStream);
        printHeader();
    }

    private void printFooter() {
        printWriter.println("</body>");
        printWriter.println("</html>");
    }

    private void printHeader() {
        printWriter.println("<!DOCTYPE html>");
        printWriter.println("<html>");
        printWriter.println("<head>");
        printWriter.println("<style>");
        printWriter.println("body {");
        printWriter.println("  font-family: monospace;");
        printWriter.println("}");
        printWriter.println("</style>");
        printWriter.println("</head>");
        printWriter.println("<body>");
    }

    // Ex: SkyBlue, Pink, MistyRose, Indigo
    public void beginColor(String colorName) {
        printWriter.printf("<span style=\"background-color: %s;\">", colorName);
    }

    public void beginColor(int r, int g, int b) {
        printWriter.printf("<span style=\"background-color:rgb(%d, %d, %d);\">", r, g, b);
    }

    public void endColor() {
        printWriter.printf("</span>");
    }

    public void print(String string) {
        printWriter.print(htmlEscape(string));
    }

    @Override
    public void close() throws Exception {
        printFooter();
        printWriter.close();
    }

    /*public static void main(String[] args) throws Exception {
        ColoredHtmlReport html = new ColoredHtmlReport(System.out);
        html.print("00 01 02 03 ");
        html.beginColor("SkyBlue");
        html.print("FF FF FF FF ");
        html.endColor();
        html.print("33 44 55 77 ");
        html.beginColor("Maroon");
        html.print("DD DD DD ");
        html.endColor();
        html.close();
    }*/
}
