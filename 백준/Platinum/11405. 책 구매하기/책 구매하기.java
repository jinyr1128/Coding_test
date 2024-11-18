import java.util.*;

public class Main {

    static final int MAX = 202;
    static final int INF = Integer.MAX_VALUE / 2;

    static int N, M;
    static int[][] capacity = new int[MAX][MAX];
    static int[][] flow = new int[MAX][MAX];
    static int[][] cost = new int[MAX][MAX];
    static List<Integer>[] graph = new ArrayList[MAX];
    static int[] dist = new int[MAX];
    static int[] parent = new int[MAX];
    static boolean[] inQueue = new boolean[MAX];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        int source = MAX - 2;
        int sink = MAX - 1;

        for (int i = 0; i < MAX; i++) {
            graph[i] = new ArrayList<>();
        }

        // 서점에서 사람으로 보내는 책 설정
        for (int i = M; i < N + M; i++) {
            int booksNeeded = sc.nextInt();
            capacity[i][sink] = booksNeeded;
            graph[i].add(sink);
            graph[sink].add(i);
        }

        // 사람에서 서점으로 보내는 책 설정
        for (int i = 0; i < M; i++) {
            int booksAvailable = sc.nextInt();
            capacity[source][i] = booksAvailable;
            graph[source].add(i);
            graph[i].add(source);
        }

        // 배송비 설정
        for (int store = 0; store < M; store++) {
            for (int person = M; person < N + M; person++) {
                int shippingCost = sc.nextInt();
                cost[store][person] = shippingCost;
                cost[person][store] = -shippingCost;
                capacity[store][person] = INF;
                graph[store].add(person);
                graph[person].add(store);
            }
        }

        System.out.println(minCostMaxFlow(source, sink));
    }

    static int minCostMaxFlow(int source, int sink) {
        int totalCost = 0;

        while (spfa(source, sink)) {
            int flowAmount = INF;

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                flowAmount = Math.min(flowAmount, capacity[u][v] - flow[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                flow[u][v] += flowAmount;
                flow[v][u] -= flowAmount;
                totalCost += flowAmount * cost[u][v];
            }
        }

        return totalCost;
    }

    static boolean spfa(int source, int sink) {
        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);
        Arrays.fill(inQueue, false);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        dist[source] = 0;
        inQueue[source] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            inQueue[u] = false;

            for (int v : graph[u]) {
                if (capacity[u][v] > flow[u][v] && dist[v] > dist[u] + cost[u][v]) {
                    dist[v] = dist[u] + cost[u][v];
                    parent[v] = u;

                    if (!inQueue[v]) {
                        queue.add(v);
                        inQueue[v] = true;
                    }
                }
            }
        }

        return dist[sink] != INF;
    }
}
