package mayton.lib;

import mayton.lib.SofarTracker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class SofarTrackerTest {

    @Test
    void createUnitLikeTrackerWithNegativeArg() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SofarTracker sofarTracker = SofarTracker.createUnitLikeTracker("", -1);
        });
    }

    @Test
    void createFileSizeTrackerWithNegativeArg() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SofarTracker sofarTracker = SofarTracker.createFileSizeTracker(-1);
        });
    }

}
