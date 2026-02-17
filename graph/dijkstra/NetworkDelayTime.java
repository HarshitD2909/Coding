import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode 743 - Network Delay Time.
 */
public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();

        for (int[] time : times) {
            adj.putIfAbsent(time[0], new ArrayList<>());
            adj.get(time[0]).add(new int[] {time[2], time[1]});
        }

        int[] dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        dis[k] = 0;
        pq.add(new int[] {0, k});

        while (!pq.isEmpty()) {
            int[] currNode = pq.poll();
            int currDist = currNode[0];
            int currVal = currNode[1];

            if (currDist > dis[currVal]) {
                continue;
            }
            if (!adj.containsKey(currVal)) {
                continue;
            }

            for (int[] neighbor : adj.getOrDefault(currVal, new ArrayList<>())) {
                if (currDist + neighbor[0] < dis[neighbor[1]]) {
                    dis[neighbor[1]] = currDist + neighbor[0];
                    pq.add(new int[] {dis[neighbor[1]], neighbor[1]});
                }
            }
        }

        dis[0] = Integer.MIN_VALUE;

        int answer = Arrays.stream(dis).max().orElse(Integer.MAX_VALUE);
        if (answer == Integer.MAX_VALUE) {
            return -1;
        }
        return answer;
    }
}
