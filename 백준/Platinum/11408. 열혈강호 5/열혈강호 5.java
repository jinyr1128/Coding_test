import java.util.*;

public class Main {
    static final int MAX = 803;
    static final int INF = (int) 1e9;

    static int[][] capacity = new int[MAX][MAX];
    static int[][] flow = new int[MAX][MAX];
    static int[][] cost = new int[MAX][MAX];
    static List<Integer>[] adj = new ArrayList[MAX];

    static int source = 801, sink = 802;
    static int N, M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        for (int i = 0; i < MAX; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            adj[source].add(i);
            adj[i].add(source);
            capacity[source][i] = 1;
        }

        for (int i = 1; i <= M; i++) {
            adj[N + i].add(sink);
            adj[sink].add(N + i);
            capacity[N + i][sink] = 1;
        }

        for (int i = 1; i <= N; i++) {
            int K = sc.nextInt();
            for (int j = 0; j < K; j++) {
                int task = sc.nextInt();
                int wage = sc.nextInt();
                adj[i].add(N + task);
                adj[N + task].add(i);
                cost[i][N + task] = wage;
                cost[N + task][i] = -wage;
                capacity[i][N + task] = 1;
            }
        }

        Pair result = mcmf(source, sink);
        System.out.println(result.flow);
        System.out.println(result.cost);
    }

    static Pair mcmf(int source, int sink) {
        int totalCost = 0, totalFlow = 0;

        while (true) {
            int[] dist = new int[MAX];
            boolean[] inQueue = new boolean[MAX];
            int[] prev = new int[MAX];
            Arrays.fill(dist, INF);
            Arrays.fill(prev, -1);

            Queue<Integer> q = new LinkedList<>();
            q.add(source);
            dist[source] = 0;
            inQueue[source] = true;

            while (!q.isEmpty()) {
                int curr = q.poll();
                inQueue[curr] = false;

                for (int next : adj[curr]) {
                    if (capacity[curr][next] - flow[curr][next] > 0 && dist[curr] + cost[curr][next] < dist[next]) {
                        dist[next] = dist[curr] + cost[curr][next];
                        prev[next] = curr;
                        if (!inQueue[next]) {
                            q.add(next);
                            inQueue[next] = true;
                        }
                    }
                }
            }

            if (prev[sink] == -1) break;

            int currFlow = INF;
            for (int i = sink; i != source; i = prev[i]) {
                currFlow = Math.min(currFlow, capacity[prev[i]][i] - flow[prev[i]][i]);
            }

            for (int i = sink; i != source; i = prev[i]) {
                totalCost += currFlow * cost[prev[i]][i];
                flow[prev[i]][i] += currFlow;
                flow[i][prev[i]] -= currFlow;
            }

            totalFlow += currFlow;
        }

        return new Pair(totalFlow, totalCost);
    }

    static class Pair {
        int flow, cost;

        Pair(int flow, int cost) {
            this.flow = flow;
            this.cost = cost;
        }
    }
}
