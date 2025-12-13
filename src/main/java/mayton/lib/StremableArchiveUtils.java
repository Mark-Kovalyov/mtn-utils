package mayton.lib;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class StremableArchiveUtils {

    /**
     *
     * @param genericArchive - possible extensions are : bz2, bzip2, gz, gzip, xz
     * @return
     * @throws IOException
     */
    public static InputStream autodetectArchiveStream(String genericArchive) throws IOException {
        String extension = genericArchive.substring(genericArchive.lastIndexOf(".")).toLowerCase();
        switch (extension) {
            // TODO: Consider: Snappy, Z
            case ".bz2", ".bzip2": return new BZip2CompressorInputStream(new FileInputStream(genericArchive));
            case ".gz",  ".gzip" : return new GZIPInputStream(new FileInputStream(genericArchive));
            case ".xz"           : return new XZCompressorInputStream(new FileInputStream(genericArchive));
            default: return new FileInputStream(genericArchive);
        }
    }

    /**
     *
     * @param genericArchive - possible extensions are : bz2, bzip2, gz, gzip, xz
     * @return
     * @throws IOException
     */
    public static OutputStream autodetectOutputArchiveStream(String genericArchive) throws IOException {
        String extension = genericArchive.substring(genericArchive.lastIndexOf(".")).toLowerCase();
        switch (extension) {
            case ".bz2", ".bzip2": return new BZip2CompressorOutputStream(new FileOutputStream(genericArchive));
            case ".gz",  ".gzip" : return new GZIPOutputStream(new FileOutputStream(genericArchive));
            case ".xz"           : return new XZCompressorOutputStream(new FileOutputStream(genericArchive));
            default: return new FileOutputStream(genericArchive);
        }
    }

}
