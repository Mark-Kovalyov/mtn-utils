package mayton.lib;

import java.util.Random;

public class SofarTrackerMain {

    public static void main(String[] args) throws InterruptedException {
        long ALL = 25_000L;
        //SofarTracker sofarTracker = SofarTracker.createFileSizeTracker(ALL);
        SofarTracker sofarTracker = SofarTracker.createUnitLikeTracker("row", ALL);
        long pos = 0;
        Random random = new Random();
        while(pos < ALL) {
            sofarTracker.update(pos);
            System.out.println(sofarTracker.toString());
            long r = (long) (6000.0 * Math.abs(random.nextGaussian()));
            Thread.sleep(r);
            pos += r;
        }
        sofarTracker.finish();
        System.out.println(sofarTracker.toString());
        Thread.sleep(5000);
        System.out.println(sofarTracker.toString());


    }

}
