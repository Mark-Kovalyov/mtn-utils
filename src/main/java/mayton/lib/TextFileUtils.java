package mayton.lib;

import java.io.*;

import static mayton.lib.StremableArchiveUtils.autodetectArchiveStream;

public class TextFileUtils {

    public static long countCachedRows(String fileName) throws IOException {
        //logger.info("autodetecting rowcount...")
        String ccrFile = fileName + ".cachedrows";
        long res = 0;
        if (new File(ccrFile).exists()) {
            try (DataInputStream reader = new DataInputStream(new FileInputStream(ccrFile))) {
                res = reader.readLong();
            }
            return res;
        } else {
            res = countRows(fileName);
            try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(ccrFile))) {
                dos.writeLong(res);
            }
            return res;
        }
    }

    public static long countRows(String fileName) throws IOException {
        long rowCnt = 0L;
        try(InputStream inputStream = autodetectArchiveStream(fileName)) {
            byte[] buf = new byte[8192];
            long res = 0;
            while ((res = inputStream.read(buf)) > 0) {
                for (int i = 0; i < res; i++) {
                    if (buf[i] == 0x0a) rowCnt = rowCnt + 1;
                }
            }
        }
        //logger.info(s"detected ${rowCnt} rows from physical file ${file}")
        return rowCnt;
    }

}
