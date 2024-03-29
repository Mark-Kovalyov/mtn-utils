package mayton.lib;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static java.lang.String.format;
import static java.time.Instant.ofEpochMilli;
import static org.apache.commons.io.FileUtils.byteCountToDisplaySize;
import static org.apache.commons.lang3.time.DurationFormatUtils.formatDuration;

public class SofarTracker {

    private static final String INTERVAL_TIME_FORMAT = "H:mm:ss";
    private static final String UNKNOWN_INTERVAL_PLACEHOLDER = "??:??:??";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public long getSize() {
        return size;
    }

    public long getPosition() {
        return position;
    }

    enum UnitTypes {
        UNITS, BYTES
    }

    private String units;

    private long size;

    private long position;

    private long startTime;

    private long currentTime;

    private UnitTypes unitType;

    private boolean finished;

    public static SofarTracker createUnitLikeTracker(String units, long size) {
        return new SofarTracker(units, size, UnitTypes.UNITS);
    }

    public static SofarTracker createFileSizeTracker(long size) {
        return new SofarTracker(null, size, UnitTypes.BYTES);
    }

    private SofarTracker(String units, long size, UnitTypes unitType) {
        if (size < 0) {
            throw new IllegalArgumentException(format("Argument was %d but expected nonnegative", position));
        }
        this.units = units;
        this.size = size;
        this.startTime = System.currentTimeMillis();
        this.currentTime = startTime;
        this.unitType = unitType;
    }

    public void update(long position) {
        if (finished) {
            throw new IllegalStateException("Unable to update! SofarTracker is finished!");
        }
        if (position < 0 || position > this.size) {
            throw new IllegalArgumentException(format("Argument was %d but expected in interval [0..size]", position));
        }
        this.position = position;
        this.currentTime = System.currentTimeMillis();
    }

    private Optional<Long> estimatedEndTime() {
        if (position == 0) {
            return Optional.empty();
        }
        return Optional.of((long) ((double) size * (currentTime - startTime) / position) + startTime);
    }

    private int calculatePercent() {
        return (int) (100L * position / size);
    }

    private String formatUnitsSpeed() {
        long deltaTimeSec = (currentTime - startTime) / 1000;
        if (deltaTimeSec == 0) {
            return "?";
        } else {
            return String.format("%d %s(s)/sec", position / deltaTimeSec, units);
        }
    }

    private String formatFileSizeSpeed() {
        long deltaTimeSec = (currentTime - startTime) / 1000;
        if (deltaTimeSec == 0) {
            return "?";
        } else {
            return String.format("%s/sec", byteCountToDisplaySize(position / deltaTimeSec));
        }
    }

    private String formatEstimatedEndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        if (estimatedEndTime().isPresent()) {
            long estimatedEntTimeValue = estimatedEndTime().get();
            return formatter.format(LocalDateTime.ofInstant(ofEpochMilli(estimatedEntTimeValue), ZoneId.systemDefault()));
        } else {
            return UNKNOWN_INTERVAL_PLACEHOLDER;
        }
    }

    @Override
    public String toString() {
        if (finished) {
            if (this.unitType == UnitTypes.BYTES) {
                return format("Done! Processed %s of %s. [ %d%% ] Time elapsed: %s",
                        byteCountToDisplaySize(position),
                        byteCountToDisplaySize(size),
                        calculatePercent(),
                        formatElapsedTime()
                );
            } else {
                return format("Done! Processed %d of %d %s(s). [ %d%% ] Time elapsed: %s",
                        position,
                        size,
                        units,
                        calculatePercent(),
                        formatElapsedTime()
                );
            }
        } else {
            if (this.unitType == UnitTypes.BYTES) {
                return format("Processed %s of %s. [ %d%% ] with avg speed: %s. Time elapsed: %s, Time remain: %s, Will end at %s",
                        byteCountToDisplaySize(position),
                        byteCountToDisplaySize(size),
                        calculatePercent(),
                        formatFileSizeSpeed(),
                        formatElapsedTime(),
                        formatTimeRemains(),
                        formatEstimatedEndTime()
                );
            } else {
                return format("Processed %d of %d %s(s). [ %d%% ] with avg speed: %s. Time elapsed: %s, Time remain: %s, Will end at %s",
                        position,
                        size,
                        units,
                        calculatePercent(),
                        formatUnitsSpeed(),
                        formatElapsedTime(),
                        formatTimeRemains(),
                        formatEstimatedEndTime()
                );
            }
        }
    }

    private String formatTimeRemains() {
        if (estimatedEndTime().isPresent() && estimatedEndTime().get() - currentTime >= 0) {
            return formatDuration(Duration.between(
                    ofEpochMilli(currentTime),
                    ofEpochMilli(estimatedEndTime().get())).toMillis(),
                    INTERVAL_TIME_FORMAT,
                    true);
        } else {
            return UNKNOWN_INTERVAL_PLACEHOLDER;
        }
    }

    private String formatElapsedTime() {
        return formatDuration(Duration.between(
                ofEpochMilli(startTime),
                ofEpochMilli(currentTime)).toMillis(),
                INTERVAL_TIME_FORMAT,
                true);
    }

    public void finish() {
        finished = true;
        position = size;
    }

}
