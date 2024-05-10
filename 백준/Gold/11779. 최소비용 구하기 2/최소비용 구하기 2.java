import java.io.*;
import java.util.*;

class Edge {
    int to, cost;
    Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

public class Main {
    static final int INF = 100000000;
    static int n, m;
    static List<Edge>[] adj;
    static int[] dist;
    static int[] prev;

    public static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int here = edge.to;
            int hereCost = edge.cost;

            if (hereCost > dist[here]) continue;

            for (Edge next : adj[here]) {
                int nextCost = hereCost + next.cost;
                if (nextCost < dist[next.to]) {
                    dist[next.to] = nextCost;
                    pq.add(new Edge(next.to, nextCost));
                    prev[next.to] = here;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        adj = new ArrayList[n + 1];
        dist = new int[n + 1];
        prev = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj[from].add(new Edge(to, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        dijkstra(A);

        System.out.println(dist[B]);

        List<Integer> path = new ArrayList<>();
        for (int node = B; node != 0; node = prev[node]) {
            path.add(node);
        }
        Collections.reverse(path);

        System.out.println(path.size());
        path.forEach(p -> System.out.print(p + " "));
    }
}
