import java.util.*;

public class Main {
    static int n, m;
    static int LOG;
    static int[][] parent, minRoad, maxRoad;
    static int[] depth;
    static List<int[]>[] adj;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }

        LOG = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        parent = new int[n + 1][LOG];
        minRoad = new int[n + 1][LOG];
        maxRoad = new int[n + 1][LOG];
        depth = new int[n + 1];
        depth[0] = -1;

        dfs(1, 0, 0);

        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                parent[i][j] = parent[parent[i][j - 1]][j - 1];
                minRoad[i][j] = Math.min(minRoad[i][j - 1], minRoad[parent[i][j - 1]][j - 1]);
                maxRoad[i][j] = Math.max(maxRoad[i][j - 1], maxRoad[parent[i][j - 1]][j - 1]);
            }
        }

        m = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int[] result = query(u, v);
            sb.append(result[0]).append(" ").append(result[1]).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int u, int p, int d) {
        parent[u][0] = p;
        depth[u] = d;

        for (int[] next : adj[u]) {
            int v = next[0];
            int w = next[1];
            if (v != p) {
                minRoad[v][0] = w;
                maxRoad[v][0] = w;
                dfs(v, u, d + 1);
            }
        }
    }

    static int[] query(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int minRes = Integer.MAX_VALUE;
        int maxRes = Integer.MIN_VALUE;

        int diff = depth[u] - depth[v];
        for (int i = 0; i < LOG; i++) {
            if ((diff & (1 << i)) != 0) {
                minRes = Math.min(minRes, minRoad[u][i]);
                maxRes = Math.max(maxRes, maxRoad[u][i]);
                u = parent[u][i];
            }
        }

        if (u == v) {
            return new int[]{minRes, maxRes};
        }

        for (int i = LOG - 1; i >= 0; i--) {
            if (parent[u][i] != parent[v][i]) {
                minRes = Math.min(minRes, Math.min(minRoad[u][i], minRoad[v][i]));
                maxRes = Math.max(maxRes, Math.max(maxRoad[u][i], maxRoad[v][i]));
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        minRes = Math.min(minRes, Math.min(minRoad[u][0], minRoad[v][0]));
        maxRes = Math.max(maxRes, Math.max(maxRoad[u][0], maxRoad[v][0]));

        return new int[]{minRes, maxRes};
    }
}
