package mayton.lib.io;

import java.io.IOException;
import java.io.OutputStream;

public class TapOutputStreamSync extends OutputStream {

    private OutputStream o1;
    private OutputStream o2;

    public TapOutputStreamSync(OutputStream o1, OutputStream o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void write(int b) throws IOException {
        o1.write(b);
        o2.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        o1.write(b);
        o2.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        o1.write(b, off, len);
        o2.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        o1.flush();
        o2.flush();
    }

    @Override
    public void close() throws IOException {
        o1.close();
        o2.close();
    }
}
