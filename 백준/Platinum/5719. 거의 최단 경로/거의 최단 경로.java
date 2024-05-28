import java.util.*;

class Main {
    static final int INF = 500000001;
    static int n, m, s, d;
    static List<List<Pair>> graph;
    static List<List<Integer>> prv;
    static boolean[][] check;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            n = sc.nextInt();
            m = sc.nextInt();
            if (n == 0 && m == 0) break;

            s = sc.nextInt();
            d = sc.nextInt();
            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            check = new boolean[501][501];

            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int p = sc.nextInt();
                graph.get(u).add(new Pair(p, v));
            }

            dijkstra();
            erase(d);
            int result = dijkstra();
            System.out.println(result);
        }

        sc.close();
    }

    static class Pair implements Comparable<Pair> {
        int cost, vertex;

        Pair(int cost, int vertex) {
            this.cost = cost;
            this.vertex = vertex;
        }

        public int compareTo(Pair p) {
            return Integer.compare(this.cost, p.cost);
        }
    }

    static int dijkstra() {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        prv = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            prv.add(new ArrayList<>());
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, s));
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int cur = current.vertex;
            int cost = current.cost;

            if (cost > dist[cur]) continue;

            for (Pair edge : graph.get(cur)) {
                if (check[cur][edge.vertex]) continue;

                int next = edge.vertex;
                int ncost = edge.cost;

                if (dist[next] == dist[cur] + ncost) {
                    prv.get(next).add(cur);
                }

                if (dist[next] > dist[cur] + ncost) {
                    prv.get(next).clear();
                    prv.get(next).add(cur);
                    dist[next] = dist[cur] + ncost;
                    pq.add(new Pair(dist[next], next));
                }
            }
        }

        return dist[d] == INF ? -1 : dist[d];
    }

    static void erase(int cur) {
        for (int prev : prv.get(cur)) {
            for (Pair edge : graph.get(prev)) {
                if (edge.vertex == cur && !check[prev][cur]) {
                    check[prev][cur] = true;
                    erase(prev);
                }
            }
        }
    }
}
