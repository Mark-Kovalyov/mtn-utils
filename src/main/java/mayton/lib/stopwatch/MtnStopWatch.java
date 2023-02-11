package mayton.lib.stopwatch;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This is a clone of
 */
public class MtnStopWatch {

    public static final class TaskInfo {

        public final String taskName;

        public final long timeNanos;

        TaskInfo(String taskName, long timeNanos) {
            this.taskName = taskName;
            this.timeNanos = timeNanos;
        }

    }

    private String id;

    private boolean keepTaskList = true;

    private final List<TaskInfo> taskList = new ArrayList<>(1);

    private long startTimeNanos;

    private String currentTaskName;

    private TaskInfo lastTaskInfo;

    private int taskCount;

    private long totalTimeNanos;

    public void start(String name) {

    }

    public void stop() {

    }

    /*public String prettyPrint(TimeUnit timeUnit) {
        StringBuilder sb = new StringBuilder(shortSummary());
        sb.append('\n');
        if (!this.keepTaskList) {
            sb.append("No task info kept");
        }
        else {
            sb.append("---------------------------------------------\n");
            sb.append("ns         %     Task name\n");
            sb.append("---------------------------------------------\n");
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMinimumIntegerDigits(9);
            nf.setGroupingUsed(false);
            NumberFormat pf = NumberFormat.getPercentInstance();
            pf.setMinimumIntegerDigits(3);
            pf.setGroupingUsed(false);
            for (TaskInfo task : getTaskInfo()) {
                sb.append(nf.format(task.getTimeNanos())).append("  ");
                sb.append(pf.format((double) task.getTimeNanos() / getTotalTimeNanos())).append("  ");
                sb.append(task.getTaskName()).append('\n');
            }
        }
        return sb.toString();
    }*/

    private TaskInfo[] getTaskInfo() {
        return new TaskInfo[0];
    }


}
