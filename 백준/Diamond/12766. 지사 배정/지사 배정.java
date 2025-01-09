import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int n, b, s, r;
    static int[] d, rd;
    static List<Pair>[] v, rv;
    static long[] sum;
    static long[][] dp;
    static int[][] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        d = new int[n + 1];
        rd = new int[n + 1];
        v = new ArrayList[n + 1];
        rv = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            v[i] = new ArrayList<>();
            rv[i] = new ArrayList<>();
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            v[start].add(new Pair(end, l));
            rv[end].add(new Pair(start, l));
        }

        dijkstra(b + 1, d, v);
        dijkstra(b + 1, rd, rv);

        List<Integer> value = new ArrayList<>();
        for (int i = 1; i <= b; i++) {
            value.add(d[i] + rd[i]);
        }
        Collections.sort(value);

        sum = new long[b + 1];
        for (int i = 1; i <= b; i++) {
            sum[i] = sum[i - 1] + value.get(i - 1);
        }

        dp = new long[s + 1][b + 1];
        p = new int[s + 1][b + 1];

        for (int i = 1; i <= b; i++) {
            dp[1][i] = sum[i] * (i - 1);
            p[1][i] = 1;
        }

        for (int i = 2; i <= s; i++) {
            dnqo(i, i, b, 0, b);
        }

        System.out.println(dp[s][b]);
    }

    static void dijkstra(int st, int[] dist, List<Pair>[] e) {
        Arrays.fill(dist, INF);
        dist[st] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.weight));
        pq.add(new Pair(st, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int td = curr.weight;
            int node = curr.node;

            if (td > dist[node]) continue;

            for (Pair next : e[node]) {
                int nd = td + next.weight;
                if (nd < dist[next.node]) {
                    dist[next.node] = nd;
                    pq.add(new Pair(next.node, nd));
                }
            }
        }
    }

    static long cost(int i, int j) {
        return (sum[j] - sum[i]) * (j - i - 1);
    }

    static void dnqo(int t, int l, int r, int pl, int pr) {
        if (l > r) return;
        int mid = (l + r) / 2;
        dp[t][mid] = Long.MAX_VALUE;

        for (int k = pl; k <= Math.min(mid - 1, pr); k++) {
            long tmp = dp[t - 1][k] + cost(k, mid);
            if (tmp < dp[t][mid]) {
                dp[t][mid] = tmp;
                p[t][mid] = k;
            }
        }

        dnqo(t, l, mid - 1, pl, p[t][mid]);
        dnqo(t, mid + 1, r, p[t][mid], pr);
    }

    static class Pair {
        int node, weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
