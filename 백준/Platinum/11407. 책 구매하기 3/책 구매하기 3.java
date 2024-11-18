import java.util.*;

public class Main {
    static final int MAX = 205;
    static final int INF = 1000000000;
    static final int SOUR = 1;
    static final int SINK = 2;

    static int[][] capacity = new int[MAX][MAX];
    static int[][] flow = new int[MAX][MAX];
    static int[][] cost = new int[MAX][MAX];
    static List<Integer>[] adj = new ArrayList[MAX];
    static int count = 0;
    static int costSum = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < MAX; i++) {
            adj[i] = new ArrayList<>();
        }

        int N = sc.nextInt();
        int M = sc.nextInt();

        for (int i = M + 3; i < M + N + 3; i++) {
            int capacityToSink = sc.nextInt();
            adj[i].add(SINK);
            adj[SINK].add(i);
            capacity[i][SINK] = capacityToSink;
        }

        for (int i = 3; i < M + 3; i++) {
            int capacityFromSource = sc.nextInt();
            adj[SOUR].add(i);
            adj[i].add(SOUR);
            capacity[SOUR][i] = capacityFromSource;
        }

        for (int i = 3; i < M + 3; i++) {
            for (int j = M + 3; j < M + N + 3; j++) {
                int cap = sc.nextInt();
                adj[i].add(j);
                adj[j].add(i);
                capacity[i][j] = cap;
            }
        }

        for (int i = 3; i < M + 3; i++) {
            for (int j = M + 3; j < M + N + 3; j++) {
                int edgeCost = sc.nextInt();
                cost[i][j] = edgeCost;
                cost[j][i] = -edgeCost;
            }
        }

        mcmf(SOUR, SINK);
        System.out.println(count);
        System.out.println(costSum);
    }

    static void mcmf(int source, int sink) {
        while (true) {
            boolean[] inQueue = new boolean[MAX];
            int[] parent = new int[MAX];
            int[] dist = new int[MAX];
            Arrays.fill(dist, INF);
            Arrays.fill(parent, -1);
            dist[source] = 0;

            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(source);
            inQueue[source] = true;

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                inQueue[cur] = false;

                for (int next : adj[cur]) {
                    if (capacity[cur][next] - flow[cur][next] > 0 && dist[cur] + cost[cur][next] < dist[next]) {
                        dist[next] = dist[cur] + cost[cur][next];
                        parent[next] = cur;

                        if (!inQueue[next]) {
                            queue.add(next);
                            inQueue[next] = true;
                        }
                    }
                }
            }

            if (parent[sink] == -1) break;

            int amount = INF;
            for (int i = sink; i != source; i = parent[i]) {
                amount = Math.min(amount, capacity[parent[i]][i] - flow[parent[i]][i]);
            }

            for (int i = sink; i != source; i = parent[i]) {
                costSum += amount * cost[parent[i]][i];
                flow[parent[i]][i] += amount;
                flow[i][parent[i]] -= amount;
            }

            count += amount;
        }
    }
}
