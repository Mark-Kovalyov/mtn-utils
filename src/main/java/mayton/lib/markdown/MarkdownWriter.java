package mayton.lib.markdown;

import org.apache.commons.lang3.tuple.Pair;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.StringJoiner;

import static java.util.Arrays.stream;

public class MarkdownWriter implements AutoCloseable {

    public enum Align {
        LEFT(" :-- "),
        CENTER(" - "),
        RIGHT(" -: ");

        public final String value;

        Align(String value) {
            this.value = value;
        }
    }

    private PrintWriter pw;

    public MarkdownWriter(Writer w) {
        pw = new PrintWriter(w);
    }

    public void header0(String text) {
        pw.println("# " + text);
    }

    public void header1(String text) {
        pw.println("## " + text);
    }

    public void header2(String text) {
        pw.println("### " + text);
    }

    public void image(String alt, String url) {
        pw.printf("![%s](%s)\n", alt, url);
    }

    public void beginSource(String language) {
        pw.printf("```%s\n", language);
    }

    public void endSource(String language) {
        pw.printf("```\n");
    }

    public void tableHeader(String ...columns) {
        final StringJoiner sj = new StringJoiner("|","|","|");
        stream(columns).forEach(s -> sj.add(s));
        pw.println(sj.toString());
        final StringJoiner sj2 = new StringJoiner("|","|","|");
        stream(columns).forEach(s -> sj2.add("-"));
        pw.println(sj2.toString());
    }

    public void tableHeaderWithAligh(Pair<String, Align> ...pairs) {
        final StringJoiner sj = new StringJoiner("|","|","|");

    }

    @Override
    public void close() throws Exception {

    }
}
