import java.util.*;

public class Main {
    static int N, M, start, end, roadCount;
    static int[] entry, time;
    static boolean[] visited;
    static List<int[]>[] road, reverseRoad;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        road = new ArrayList[N + 1];
        reverseRoad = new ArrayList[N + 1];
        entry = new int[N + 1];
        time = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            road[i] = new ArrayList<>();
            reverseRoad[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            road[a].add(new int[]{b, c});
            reverseRoad[b].add(new int[]{a, c});
            entry[b]++;
        }

        start = sc.nextInt();
        end = sc.nextInt();

        findDistance(start);
        findLongestRoad(end);

        System.out.println(time[end]);
        System.out.println(roadCount);
    }

    static void findDistance(int s) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{s, 0});

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cur = curr[0];
            int curTime = curr[1];

            for (int[] next : road[cur]) {
                int nextNode = next[0];
                int nTime = next[1];

                time[nextNode] = Math.max(time[nextNode], curTime + nTime);
                entry[nextNode]--;

                if (entry[nextNode] == 0) {
                    q.add(new int[]{nextNode, time[nextNode]});
                }
            }
        }
    }

    static void findLongestRoad(int e) {
        Queue<Integer> q = new LinkedList<>();
        q.add(e);
        visited[e] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int[] prev : reverseRoad[cur]) {
                int prevNode = prev[0];
                int prevTime = prev[1];

                if (time[cur] - prevTime == time[prevNode]) {
                    roadCount++;
                    if (!visited[prevNode]) {
                        visited[prevNode] = true;
                        q.add(prevNode);
                    }
                }
            }
        }
    }
}
