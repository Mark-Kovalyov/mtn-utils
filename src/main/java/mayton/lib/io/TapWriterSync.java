package mayton.lib.io;

import java.io.IOException;
import java.io.Writer;

public class TapWriterSync extends Writer {

    private Writer w1;
    private Writer w2;

    public TapWriterSync(Writer w1, Writer w2) {
        this.w1 = w1;
        this.w2 = w2;
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        w1.write(cbuf, off, len);
        w2.write(cbuf, off, len);
    }

    @Override
    public void flush() throws IOException {
        w1.flush();
        w2.flush();
    }

    @Override
    public void close() throws IOException {
        w1.close();
        w2.close();
    }
}
