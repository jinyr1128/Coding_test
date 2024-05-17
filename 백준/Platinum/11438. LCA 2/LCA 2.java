import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] tree;
    static int[] depth;
    static int[][] parent;
    static final int MAX = 17; // 2^17 > 100000

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }

        M = sc.nextInt();
        int[][] queries = new int[M][2];
        for (int i = 0; i < M; i++) {
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }

        depth = new int[N + 1];
        parent = new int[N + 1][MAX];
        depth[0] = -1; // 루트의 부모의 깊이는 -1로 설정

        dfs(1, 0); // 루트 노드는 1, 부모 노드는 0 (루트는 부모가 없음)

        for (int j = 1; j < MAX; j++) {
            for (int i = 1; i <= N; i++) {
                if (parent[i][j - 1] != 0) {
                    parent[i][j] = parent[parent[i][j - 1]][j - 1];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] query : queries) {
            sb.append(lca(query[0], query[1])).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void dfs(int current, int par) {
        depth[current] = depth[par] + 1;
        parent[current][0] = par;

        for (int next : tree[current]) {
            if (next != par) {
                dfs(next, current);
            }
        }
    }

    static int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        for (int i = MAX - 1; i >= 0; i--) {
            if (depth[u] - depth[v] >= (1 << i)) {
                u = parent[u][i];
            }
        }

        if (u == v) {
            return u;
        }

        for (int i = MAX - 1; i >= 0; i--) {
            if (parent[u][i] != parent[v][i]) {
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        return parent[u][0];
    }
}
