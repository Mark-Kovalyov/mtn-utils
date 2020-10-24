package mayton.lib.logparser;

import org.apache.commons.lang3.Validate;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.regex.Pattern;

public class LogParser implements Iterable<LogMessage> {

    private Pattern regexDatePattern;
    private File file;

    public LogParser(File file, String regexDatePattern) {
        Validate.notNull(file);
        this.file = file;
        this.regexDatePattern = Pattern.compile(regexDatePattern);
    }

    class LogMessageIterator implements Iterator<LogMessage> {

        private BufferedReader bufferedReader;
        private Pattern regexDatePattern;
        private String line;
        private String prevLine;
        private StringBuilder stringBuilder;
        private boolean isFirstLine = true;
        private boolean logFinished = false;

        public LogMessageIterator(Reader reader, Pattern regexDatePattern) {
            this.bufferedReader = new BufferedReader(reader);
            this.regexDatePattern = regexDatePattern;
            this.stringBuilder = new StringBuilder();
            onStartLogFile();
        }

        private void onStartLogFile() {
            this.stringBuilder = new StringBuilder();
        }

        private void onReceiveLine(String line) {

        }

        private void onReceiveLineWithTimestamp(String line) {

        }

        private void onEndLogFile() {

        }

        private String safeReadLine() {
            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        private boolean mathesLineWithTimestamp(String line) {
            return regexDatePattern.matcher(line).matches();
        }

        @Override
        public boolean hasNext() {
            if (logFinished) {
                return false;
            }
            if (isFirstLine) {
                onStartLogFile();
            }
            line = safeReadLine();
            if (line == null) {
                onEndLogFile();
                logFinished = true;
            } else {
                if (mathesLineWithTimestamp(line)) {
                    onReceiveLineWithTimestamp(line);
                } else {
                    onReceiveLine(line);
                }
            }
            return false;
        }

        @Override
        public LogMessage next() {
            LogMessage message = new LogMessage(
                    stringBuilder.toString(),
                    LogMessageLevel.INFO,
                    LocalDateTime.now());
            return message;
        }
    }

    @Override
    public Iterator<LogMessage> iterator() {
        try {
            return new LogMessageIterator(new FileReader(file), regexDatePattern);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
