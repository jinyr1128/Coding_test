import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int N, M, K, S, D;
    static List<Edge>[] graph;
    static int[][] dist;
    static int accumulatedTax = 0;

    static class Edge {
        int target, cost;
        public Edge(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }
    }

    static class Node implements Comparable<Node> {
        int idx, count, cost;
        public Node(int idx, int count, int cost) {
            this.idx = idx;
            this.count = count;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄 입력: 도시 수, 도로 수, 세금 인상 횟수
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 두 번째 줄 입력: 출발 도시와 도착 도시
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 도로 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        // 최단 거리 테이블 초기화
        dist = new int[N + 1][N];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
        }

        // 다익스트라 알고리즘으로 초기 최소 통행료 계산
        dijkstra();
        calculate();

        // 세금 인상에 따른 최소 통행료 계산
        for (int i = 0; i < K; i++) {
            int taxIncrease = Integer.parseInt(br.readLine());
            accumulatedTax += taxIncrease;
            calculate();
        }
    }

    // 다익스트라 알고리즘
    public static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(S, 0, 0));
        dist[S][0] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currIdx = current.idx;
            int currCount = current.count;
            int currCost = current.cost;

            if (dist[currIdx][currCount] < currCost) continue;

            for (Edge edge : graph[currIdx]) {
                int nextIdx = edge.target;
                int nextCost = edge.cost;

                if (currCount + 1 < N && dist[nextIdx][currCount + 1] > currCost + nextCost) {
                    dist[nextIdx][currCount + 1] = currCost + nextCost;
                    pq.offer(new Node(nextIdx, currCount + 1, currCost + nextCost));
                }
            }
        }
    }

    // 세금 인상에 따라 계산된 최소 통행료를 출력
    public static void calculate() {
        int minCost = INF;
        for (int i = 1; i < N; i++) {
            if (dist[D][i] < INF) {
                minCost = Math.min(minCost, dist[D][i] + i * accumulatedTax);
            }
        }
        System.out.println(minCost == INF ? -1 : minCost);
    }
}
