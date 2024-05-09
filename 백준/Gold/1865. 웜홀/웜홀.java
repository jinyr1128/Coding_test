import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE / 2; // 오버플로우 방지

    static class Edge {
        int source, target, weight;
        Edge(int source, int target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }
    }

    static boolean bellmanFord(int n, List<Edge> edges, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean updated;

        for (int i = 1; i < n; i++) {
            updated = false;
            for (Edge e : edges) {
                if (dist[e.source] != INF && dist[e.target] > dist[e.source] + e.weight) {
                    dist[e.target] = dist[e.source] + e.weight;
                    updated = true;
                }
            }
            if (!updated) break; // 더 이상 갱신되지 않으면 종료
        }

        // 음수 사이클 존재 여부 확인
        for (Edge e : edges) {
            if (dist[e.source] != INF && dist[e.target] > dist[e.source] + e.weight) {
                return true; // 음수 사이클 존재
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T)); // 양방향
            }
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, -T)); // 웜홀
            }

            boolean hasNegativeCycle = false;
            for (int i = 1; i <= N; i++) {
                if (bellmanFord(N, edges, i)) {
                    hasNegativeCycle = true;
                    break;
                }
            }

            if (hasNegativeCycle) sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.print(sb);
    }
}

