import mayton.lib.logparser.LogMessage;
import mayton.lib.logparser.LogParser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class LogParserTest {

    @Test
    @Disabled
    public void testEmptyStream() {
        LogParser logParser = new LogParser(new File("/dev/null"), "\\d+");
        Iterator<LogMessage> iterator = logParser.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    @Disabled
    public void test() {
        //LogParser logParser = new LogParser(new File("src/test/resources/dht-listeners-AM2.txt"), "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} ");
        LogParser logParser = new LogParser(new File("src/test/resources/dht-listeners-AM2.txt"), "^\\d{4}.+");
        Iterator<LogMessage> iterator = logParser.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("2020-06-09 21:58:01 [INFO ] 102 : dhtlisteners.AM2 Started A-Mule/4672 listener, threadId = 102",
                iterator.next().messageBody);
        assertTrue(iterator.hasNext());
        assertEquals("2020-06-09 22:11:03 [WARN ] 102 : dhtlisteners.AM2 !\n" +
                        "the8472.bencode.Tokenizer$BDecodingException: unexpected character 0x48 at offset 0\n" +
                        "\tat the8472.bencode.Tokenizer.tokenize(Tokenizer.java:278) ~[bt-dht-1.9.jar!/:1.9]\n" +
                        "\tat the8472.bencode.BDecoder.decodeInternal(BDecoder.java:151) ~[bt-dht-1.9.jar!/:1.9]\n" +
                        "\tat the8472.bencode.BDecoder.decode(BDecoder.java:128) ~[bt-dht-1.9.jar!/:1.9]\n" +
                        "\tat mayton.network.dhtobserver.DhtListener.decodeCommand(DhtListener.java:89) [classes!/:?]\n" +
                        "\tat mayton.network.dhtobserver.DhtListener.run(DhtListener.java:71) [classes!/:?]\n" +
                        "\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128) [?:?]\n" +
                        "\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628) [?:?]\n" +
                        "\tat java.lang.Thread.run(Thread.java:834) [?:?]",
                iterator.next().messageBody);
    }

}
