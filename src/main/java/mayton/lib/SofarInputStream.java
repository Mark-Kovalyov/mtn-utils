package mayton.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.locks.StampedLock;

public class SofarInputStream extends InputStream {

    private InputStream is;

    private long size;

    private long position;

    private StampedLock stampedLock = new StampedLock();

    private SofarTracker sofarTracker;

    public String sofarString() {
        synchronized (sofarTracker) {
            return sofarTracker.toString();
        }
    }

    public SofarInputStream(InputStream is, long size) {
        this.sofarTracker = SofarTracker.createFileSizeTracker(size);
        this.position = 0;
        this.size = size;
        this.is = is;
    }

    @Override
    public int read() throws IOException {
        position++;
        synchronized (sofarTracker) {
            sofarTracker.update(position);
        }
        return is.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        int bytes = is.read(b);
        position +=bytes;
        synchronized (sofarTracker) {
            sofarTracker.update(position);
        }
        return bytes;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int bytes = is.read(b, off, len);
        position += bytes;
        synchronized (sofarTracker) {
            sofarTracker.update(position);
        }
        return bytes;
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        throw new RuntimeException("Not supported");
    }

    @Override
    public byte[] readNBytes(int len) throws IOException {
        throw new RuntimeException("Not supported");
    }

    @Override
    public int readNBytes(byte[] b, int off, int len) throws IOException {
        throw new RuntimeException("Not supported");
    }

    @Override
    public long skip(long n) throws IOException {
        throw new RuntimeException("Not supported");
    }

    @Override
    public void close() throws IOException {
        synchronized (sofarTracker) {
            sofarTracker.finish();
        }
        is.close();
    }

    @Override
    public void reset() throws IOException {
        throw new RuntimeException("Not supported");
    }

    @Override
    public long transferTo(OutputStream out) throws IOException {
        throw new RuntimeException("Not supported");
    }
}
