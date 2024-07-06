package mayton.lib;

public class ThreadUtils {

    public static void sleep(int ms) {
        try {Thread.sleep(ms);} catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

}
