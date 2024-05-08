import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100000000;  // 충분히 큰 수, 800 * 1000 * 200

    static class Node implements Comparable<Node> {
        int vertex, weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static List<List<Node>> adjList;
    static int N, E;

    private static int[] dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (current.weight > dist[current.vertex]) continue;

            for (Node next : adjList.get(current.vertex)) {
                if (dist[next.vertex] > dist[current.vertex] + next.weight) {
                    dist[next.vertex] = dist[current.vertex] + next.weight;
                    pq.add(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList.get(u).add(new Node(v, w));
            adjList.get(v).add(new Node(u, w));  // 양방향 그래프
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] distFrom1 = dijkstra(1);
        int[] distFromV1 = dijkstra(v1);
        int[] distFromV2 = dijkstra(v2);

        long route1 = (long) distFrom1[v1] + distFromV1[v2] + distFromV2[N];
        long route2 = (long) distFrom1[v2] + distFromV2[v1] + distFromV1[N];

        long result = Math.min(route1, route2);
        if (result >= INF) result = -1;

        System.out.println(result);
    }
}
