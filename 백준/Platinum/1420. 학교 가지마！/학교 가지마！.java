import java.util.*;

public class Main {
    static class Edge {
        int to, capacity, flow;
        Edge reverse;

        Edge(int to, int capacity) {
            this.to = to;
            this.capacity = capacity;
            this.flow = 0;
        }

        int residual() {
            return capacity - flow;
        }

        void addFlow(int flow) {
            this.flow += flow;
            this.reverse.flow -= flow;
        }
    }

    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int N, M, S, T;
    static List<Edge>[] adj;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();

        char[][] grid = new char[N][M];
        adj = new ArrayList[2 * N * M];
        for (int i = 0; i < 2 * N * M; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            grid[i] = sc.nextLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == '#') continue;

                int inNode = node(i, j, false);
                int outNode = node(i, j, true);
                addEdge(inNode, outNode, 1);

                if (grid[i][j] == 'K') {
                    S = outNode;
                } else if (grid[i][j] == 'H') {
                    T = inNode;
                }

                for (int d = 0; d < 4; d++) {
                    int ni = i + dr[d];
                    int nj = j + dc[d];

                    if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                    if (grid[ni][nj] == '#') continue;

                    int neighborIn = node(ni, nj, false);
                    addEdge(outNode, neighborIn, Integer.MAX_VALUE);
                }
            }
        }

        if (Math.abs(S / (2 * M) - T / (2 * M)) + Math.abs(S / 2 % M - T / 2 % M) == 1) {
            System.out.println("-1");
            return;
        }

        System.out.println(maxFlow());
    }

    static int node(int row, int col, boolean isOut) {
        return (row * M + col) * 2 + (isOut ? 1 : 0);
    }

    static void addEdge(int from, int to, int capacity) {
        Edge forward = new Edge(to, capacity);
        Edge backward = new Edge(from, 0);

        forward.reverse = backward;
        backward.reverse = forward;

        adj[from].add(forward);
        adj[to].add(backward);
    }

    static int maxFlow() {
        int totalFlow = 0;

        while (true) {
            Edge[] prev = new Edge[2 * N * M];
            Queue<Integer> q = new LinkedList<>();
            q.add(S);

            while (!q.isEmpty()) {
                int curr = q.poll();

                for (Edge edge : adj[curr]) {
                    if (prev[edge.to] == null && edge.to != S && edge.residual() > 0) {
                        prev[edge.to] = edge;
                        q.add(edge.to);
                    }
                }
            }

            if (prev[T] == null) break;

            int flow = Integer.MAX_VALUE;
            for (int cur = T; cur != S; cur = prev[cur].reverse.to) {
                flow = Math.min(flow, prev[cur].residual());
            }

            for (int cur = T; cur != S; cur = prev[cur].reverse.to) {
                prev[cur].addFlow(flow);
            }

            totalFlow += flow;
        }

        return totalFlow;
    }
}
