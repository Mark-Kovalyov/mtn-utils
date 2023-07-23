package mayton.lib.logparser;

import javax.annotation.concurrent.Immutable;
import java.time.LocalDateTime;

@Immutable
public class LogMessage {

    public final String messageBody;
    public final LogMessageLevel level;
    public final LocalDateTime localDateTime;

    public LogMessage(String messageBody, LogMessageLevel level, LocalDateTime localDateTime) {
        this.messageBody = messageBody;
        this.level = level;
        this.localDateTime = localDateTime;
    }
}
