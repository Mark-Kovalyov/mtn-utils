package mayton.lib;

import mayton.lib.encoders.SnowflakeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnowflakeGeneratorTest {

    @Test
    void test3() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SnowflakeGenerator(1024),
                "Expected doThing() to throw, but it didn't"
        );
    }

    @Test
    void test() {

        // 1:41:10:12
        assertEquals(0L, SnowflakeGenerator.composeId(0,0,0));

        // 41 : 0 bits : 0 bits
        assertEquals(0b0_11111111111111111111111111111111111111111_0000000000_000000000000L,
                SnowflakeGenerator.composeId(0b11111111111111111111111111111111111111111L,0L,0L));

        // 0 : 10 : 0 bits
        assertEquals(0b1111111111_000000000000L,
                SnowflakeGenerator.composeId(0L, 1023, 0L));

        // 0 : 0 : 12 bits
        assertEquals(0b111111111111L,
                SnowflakeGenerator.composeId(0L, 0L, 4095L));

    }

}
