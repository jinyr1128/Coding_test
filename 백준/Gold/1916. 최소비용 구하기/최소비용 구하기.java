import java.util.*;
import java.io.*;

public class Main {
    static final int INF = 100000000;  // 무한대 값을 설정 (버스 비용 최대값보다 큰 값)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 도시 개수
        int M = Integer.parseInt(br.readLine());  // 버스 개수

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);
            graph.get(u).add(new Node(v, cost));
        }

        String[] lastLine = br.readLine().split(" ");
        int start = Integer.parseInt(lastLine[0]);
        int end = Integer.parseInt(lastLine[1]);

        int[] distance = dijkstra(N, graph, start);

        System.out.println(distance[end]);
    }

    static int[] dijkstra(int N, List<List<Node>> graph, int start) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (distance[current.vertex] < current.cost) continue;

            for (Node neighbor : graph.get(current.vertex)) {
                int newDist = distance[current.vertex] + neighbor.cost;
                if (newDist < distance[neighbor.vertex]) {
                    distance[neighbor.vertex] = newDist;
                    pq.offer(new Node(neighbor.vertex, newDist));
                }
            }
        }
        return distance;
    }

    static class Node {
        int vertex;
        int cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}
