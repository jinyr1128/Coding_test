import java.util.*;

public class Main {
    static final int MAX = 2005;
    static final int INF = 1000000000;

    static int[][] capacity = new int[MAX][MAX];
    static int[][] flow = new int[MAX][MAX];
    static int[][] cost = new int[MAX][MAX];
    static List<Integer>[] adj = new ArrayList[MAX];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            for (int i = 0; i < MAX; i++) {
                adj[i] = new ArrayList<>();
            }

            for (int[] row : capacity) {
                Arrays.fill(row, 0);
            }
            for (int[] row : flow) {
                Arrays.fill(row, 0);
            }
            for (int[] row : cost) {
                Arrays.fill(row, INF);
            }

            int source = N * 2 + 2;
            int sink = N * 2 + 3;

            addEdge(source, 1 * 2, 2, 0);
            addEdge(N * 2 + 1, sink, 2, 0);
            addEdge(1 * 2, 1 * 2 + 1, 2, 0);
            addEdge(N * 2, N * 2 + 1, 2, 0);

            for (int i = 2; i < N; i++) {
                addEdge(i * 2, i * 2 + 1, 1, 0);
            }

            for (int i = 0; i < M; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                int edgeCost = sc.nextInt();
                addEdge(from * 2 + 1, to * 2, 1, edgeCost);
            }

            System.out.println(mcmf(source, sink));
        }

        sc.close();
    }

    static void addEdge(int from, int to, int cap, int c) {
        adj[from].add(to);
        adj[to].add(from);
        capacity[from][to] = cap;
        cost[from][to] = c;
        cost[to][from] = -c;
    }

    static int mcmf(int source, int sink) {
        int totalCost = 0;

        while (true) {
            int[] parent = new int[MAX];
            int[] dist = new int[MAX];
            boolean[] inQueue = new boolean[MAX];
            Arrays.fill(parent, -1);
            Arrays.fill(dist, INF);

            dist[source] = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(source);
            inQueue[source] = true;

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                inQueue[curr] = false;

                for (int next : adj[curr]) {
                    if (capacity[curr][next] - flow[curr][next] > 0 && dist[curr] + cost[curr][next] < dist[next]) {
                        dist[next] = dist[curr] + cost[curr][next];
                        parent[next] = curr;

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
                totalCost += amount * cost[parent[i]][i];
                flow[parent[i]][i] += amount;
                flow[i][parent[i]] -= amount;
            }
        }

        return totalCost;
    }
}
