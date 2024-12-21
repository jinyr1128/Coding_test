import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;
    static int[][] capacity, flow;
    static List<Integer>[] graph;
    static int[] level, work, chk;
    static int n, m, s, t;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        s = sc.nextInt();
        t = sc.nextInt() + n;

        capacity = new int[2 * n + 1][2 * n + 1];
        flow = new int[2 * n + 1][2 * n + 1];
        graph = new ArrayList[2 * n + 1];
        level = new int[2 * n + 1];
        work = new int[2 * n + 1];
        chk = new int[2 * n + 1];

        for (int i = 0; i <= 2 * n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            int cost = sc.nextInt();
            addEdge(i, i + n, cost);
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            addEdge(a + n, b, INF);
            addEdge(b + n, a, INF);
        }

        solve();
        sc.close();
    }

    static void addEdge(int u, int v, int cap) {
        graph[u].add(v);
        graph[v].add(u);
        capacity[u][v] = cap;
    }

    static boolean bfs() {
        Arrays.fill(level, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        level[s] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : graph[curr]) {
                if (level[next] == -1 && capacity[curr][next] - flow[curr][next] > 0) {
                    level[next] = level[curr] + 1;
                    queue.add(next);
                }
            }
        }
        return level[t] != -1;
    }

    static int dfs(int curr, int totalFlow) {
        if (curr == t) return totalFlow;

        for (int i = work[curr]; i < graph[curr].size(); i++) {
            int next = graph[curr].get(i);

            if (level[next] == level[curr] + 1 && capacity[curr][next] - flow[curr][next] > 0) {
                int pushed = dfs(next, Math.min(totalFlow, capacity[curr][next] - flow[curr][next]));

                if (pushed > 0) {
                    flow[curr][next] += pushed;
                    flow[next][curr] -= pushed;
                    return pushed;
                }
            }
            work[curr]++;
        }

        return 0;
    }

    static int dinic() {
        int maxFlow = 0;

        while (bfs()) {
            Arrays.fill(work, 0);

            while (true) {
                int flow = dfs(s, INF);
                if (flow == 0) break;
                maxFlow += flow;
            }
        }

        return maxFlow;
    }

    static void solve() {
        dinic();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        chk[s] = 1;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : graph[curr]) {
                if (chk[next] == 0 && capacity[curr][next] - flow[curr][next] > 0) {
                    chk[next] = 1;
                    queue.add(next);
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (chk[i] == 1 && chk[i + n] == 0) {
                result.add(i);
            }
        }

        Collections.sort(result);
        for (int city : result) {
            System.out.print(city + " ");
        }
    }
}
