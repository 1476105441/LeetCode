package contest.year2023.m4.d23;

/**
 * 2651.计算列车到达时间
 *
 * @author wjs 2023/6/11
 */
public class No1 {
    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime + delayedTime) % 24;
    }
}
