package mayton.lib;

import org.apache.commons.lang3.Validate;

import java.io.Reader;

public class LogParser {

    private String regexDatePattern;
    private Reader reader;
    private StringBuilder stringBuilder = new StringBuilder();

    /**
     * Create LogParser with ISO-8601 date format
     * @param reader
     */
    public LogParser(Reader reader) {
        Validate.notNull(reader);
        this.reader = reader;
    }

    public LogParser(Reader reader, String regexDatePattern) {
        Validate.notNull(reader);
        this.reader = reader;
        this.regexDatePattern = regexDatePattern;
    }

    public String nextMessage() {
        // TODO
        return stringBuilder.toString();
    }

}
