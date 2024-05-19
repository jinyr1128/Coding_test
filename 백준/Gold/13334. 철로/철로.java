import java.util.*;

public class Main {
    static class Pair {
        int start, end;

        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int h = sc.nextInt();
            int o = sc.nextInt();
            int start = Math.min(h, o);
            int end = Math.max(h, o);
            pairs.add(new Pair(start, end));
        }

        int d = sc.nextInt();

        // Sort pairs based on end position, then start position
        pairs.sort((a, b) -> {
            if (a.end == b.end) {
                return Integer.compare(a.start, b.start);
            }
            return Integer.compare(a.end, b.end);
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int maxCount = 0;

        for (Pair pair : pairs) {
            if (pair.end - pair.start > d) {
                continue;
            }
            pq.add(pair.start);

            while (!pq.isEmpty() && pq.peek() < pair.end - d) {
                pq.poll();
            }

            maxCount = Math.max(maxCount, pq.size());
        }

        System.out.println(maxCount);
    }
}
