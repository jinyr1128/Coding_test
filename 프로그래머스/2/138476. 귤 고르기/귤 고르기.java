import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i: tangerine) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> map.get(o2) - map.get(o1));
        pq.addAll(map.keySet());

        int count = 0;
        while (k > 0) {
            int key = pq.poll();
            int value = map.get(key);
            if (value <= k) {
                k -= value;
            } else {
                k = 0;
            }
            count++;
        }

        return count;
    }
}
