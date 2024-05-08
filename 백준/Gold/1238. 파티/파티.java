import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int index, cost;

    Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, M, X;
    static List<List<Node>> graph, reverseGraph;

    private static int[] dijkstra(int start, List<List<Node>> graph) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (current.cost > dist[current.index]) continue;
            for (Node next : graph.get(current.index)) {
                if (dist[next.index] > dist[current.index] + next.cost) {
                    dist[next.index] = dist[current.index] + next.cost;
                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, t));
            reverseGraph.get(v).add(new Node(u, t)); // 뒤집은 그래프에 추가
        }

        int[] toX = dijkstra(X, reverseGraph); // 각 마을에서 X로 가는 최단 시간
        int[] fromX = dijkstra(X, graph); // X에서 각 마을로 돌아오는 최단 시간

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            if (i != X) { // X 마을 제외
                maxTime = Math.max(maxTime, toX[i] + fromX[i]);
            }
        }

        System.out.println(maxTime);
    }
}
