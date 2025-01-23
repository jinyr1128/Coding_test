import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int value;
        Node left, right;

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        Node update(int l, int r, int pos) {
            if (pos < l || pos > r) return this;
            if (l == r) return new Node(value + 1, null, null);

            int mid = (l + r) >> 1;
            return new Node(value + 1, 
                left.update(l, mid, pos), 
                right.update(mid + 1, r, pos));
        }
    }

    static final int MAXN = 100001;
    static final int LOG = 17;

    static int[] weights = new int[MAXN];
    static int[] sortedWeights = new int[MAXN];
    static int[] level = new int[MAXN];
    static int[][] parent = new int[MAXN][LOG];
    static List<Integer>[] adj = new ArrayList[MAXN];
    static Node[] roots = new Node[MAXN];
    static Node nullNode;
    static int size;
    static Map<Integer, Integer> weightMap = new HashMap<>();

    static void dfs(int cur, int prev) {
        parent[cur][0] = prev;
        level[cur] = level[prev] + 1;

        roots[cur] = (prev == 0 ? nullNode : roots[prev]).update(1, size, weights[cur]);

        for (int next : adj[cur]) {
            if (next != prev) dfs(next, cur);
        }
    }

    static void preprocessLCA(int n) {
        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                if (parent[i][j - 1] != 0) {
                    parent[i][j] = parent[parent[i][j - 1]][j - 1];
                }
            }
        }
    }

    static int findLCA(int u, int v) {
        if (level[u] < level[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = level[u] - level[v];
        for (int i = 0; i < LOG; i++) {
            if ((diff & (1 << i)) != 0) u = parent[u][i];
        }

        if (u == v) return u;

        for (int i = LOG - 1; i >= 0; i--) {
            if (parent[u][i] != parent[v][i]) {
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        return parent[u][0];
    }

    static int query(Node a, Node b, Node c, Node d, int l, int r, int k) {
        if (l == r) return l;
        int mid = (l + r) >> 1;

        int count = a.left.value + b.left.value - c.left.value - d.left.value;

        if (count >= k) {
            return query(a.left, b.left, c.left, d.left, l, mid, k);
        } else {
            return query(a.right, b.right, c.right, d.right, mid + 1, r, k - count);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
            sortedWeights[i] = weights[i];
        }

        Arrays.sort(sortedWeights, 1, n + 1);
        size = 0;
        for (int i = 1; i <= n; i++) {
            if (!weightMap.containsKey(sortedWeights[i])) {
                weightMap.put(sortedWeights[i], ++size);
            }
        }

        for (int i = 1; i <= n; i++) {
            weights[i] = weightMap.get(weights[i]);
        }

        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            adj[v].add(u);
        }

        nullNode = new Node(0, null, null);
        nullNode.left = nullNode.right = nullNode;

        dfs(1, 0);
        preprocessLCA(n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int lca = findLCA(u, v);
            int res = query(roots[u], roots[v], roots[lca], (lca == 1 ? nullNode : roots[parent[lca][0]]), 1, size, k);
            sb.append(sortedWeights[res]).append('\n');
        }

        System.out.print(sb);
    }
}
