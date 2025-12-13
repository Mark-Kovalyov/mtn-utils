package mayton.lib.web;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class CookieExporter {

    public static Cookie byNameValDom(String domain, String name, String value) {
        return new Cookie(domain, true, "/", false, Integer.MAX_VALUE, name, value);
    }

    public static Cookie byNameVal(String name, String value) {
        return new Cookie("youtube.com", true, "/", false, Integer.MAX_VALUE, name, value);
    }

    public static void export(Writer writer) {
        PrintWriter pw = new PrintWriter(writer);
        pw.println("# Netscape HTTP Cookie File");
        List<Cookie> clist = Arrays.asList(
                byNameVal("APISID",     "jghGDHG-iUsBcbKD/AvgnAe0DgV5qRFJz7"),
                byNameVal("HSID",       "AUXGjW8iqVkM38FSS"),
                byNameVal("LOGIN_INFO", "AFmmF2swRQIgcTe3kDST67PXys6cy_dhgxkIRRhmsZZnfNXlXxEalpACIQDkXgQNkKR3XOc7ZKCpQoo1CeWo1B8XSB0X4vUcGOwezQ:QUQ3MjNmektRdDZhTkdwRm1ya2dxeFpMa01LSDJHU3VheEhsYjM4MGtHR2tWbXFWNWtEUlFqS1NIbUZLb1ljaHl0cUxtMjl0QmlJSGJITmhLUnhWb2FfVnV5NmRzM0FfWlVWaHlkSlZoOW9PZ0lXQVd6UXlBTTVfenZOYUpIc3M4a1FGb2VLZUFiU3NwT0NqNEt5SWQ5TEQtZ1FkUWZIOUJ3"),
                byNameVal("PREF",       "f6=80&f7=100&tz=Europe.Kiev&f4=4000000&f5=20000"),
                byNameVal("SAPISID",    "xzpARieE7Ru-46GF/AIWVgqlFWr1eSvRhV"),
                byNameVal("SID",        "g.a000uAilsxqFmmemNBGHxGQxgRMHJuY6fiGXo2f7XJTXro8sEpjCPzdjYPO86HhlrSuI4I18QAACgYKAckSARESFQHGX2MipF5TA9jZO9PBA7InoLIZaxoVAUF8yKpXZpnpi7efOIQS_ZODNZ_p0076"),
                byNameVal("SIDCC",      "AKEyXzXUxXKMHxFIj_uq7Fu0wKgJlNsYHgbVkcAGT_LoELk2CIuwei3vyAN81n-9a9PiauVgYno"),
                byNameVal("SSID",       "A8Ktfv2ktPSs0OGY1"),
                byNameVal("VISITOR_INFO1_LIVE",            "N0AN5klF8o0"),
                byNameVal("VISITOR_PRIVACY_METADATA",      "CgJVQRIEGgAgLg%3D%3D"),
                byNameVal("YSC",        "yjwkWs_9ldc")
        );
        for(Cookie cc : clist) {
            pw.println(cc.toTsv());
        }
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        export(new FileWriter("c:/video/cookies.csv"));
    }

}
