import mayton.lib.SofarTracker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SofarTrackerTest {

    @Test
    public void createUnitLikeTrackerWithNegativeArg() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SofarTracker sofarTracker = SofarTracker.createUnitLikeTracker("", -1);
        });
    }

    @Test
    public void createFileSizeTrackerWithNegativeArg() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SofarTracker sofarTracker = SofarTracker.createFileSizeTracker(-1);
        });
    }

}
