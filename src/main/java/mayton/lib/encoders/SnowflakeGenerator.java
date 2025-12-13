package mayton.lib.encoders;

import org.apache.commons.lang3.Validate;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

public class SnowflakeGenerator {

    private long machineId;

    private AtomicLong seq = new AtomicLong(0L);

    public SnowflakeGenerator(long machineId) {
        Validate.inclusiveBetween(0, 1023, machineId);
        this.machineId = machineId;
    }

    public static long composeId(long millis, long machineId, long seqval) {
        // | 1:41:10:12
        // | tttttttttttttttttttttttttttttttttttttttttiiiiiiiiiissssssssssss
        // | 41 bit timestamp                         | Mach ID | Sequence  |
        return millis << 22 | machineId << 12 | seqval & 0xFFF;
    }

    /**
     * Snowflakes are 64 bits in binary. (Only 63 are used to fit in a signed integer.) The first 41 bits are a
     * timestamp, representing milliseconds since the chosen epoch. The next 10 bits represent a machine ID,
     * preventing clashes. Twelve more bits represent a per-machine sequence number, to allow creation of
     * multiple snowflakes in the same millisecond. The final number is generally serialized in decimal.
     *
     */
    public long nextId() {
        Instant instant = Instant.now();
        long millis = instant.getNano() / 1000;
        long seqval = seq.incrementAndGet();
        return composeId(millis, machineId, seqval);
    }

}
