import java.io.*;
import java.util.*;

public class Main {

    static int vertexNum, edgeNum, blueNum;

    // Union-Find 구조체
    static class DisjointSet {
        int[] parent, rank;

        DisjointSet(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
            }
        }

        int find(int u) {
            if (parent[u] == u) return u;
            return parent[u] = find(parent[u]);
        }

        void union(int u, int v) {
            u = find(u);
            v = find(v);
            if (u == v) return;

            if (rank[u] < rank[v]) {
                parent[u] = v;
            } else {
                parent[v] = u;
                if (rank[u] == rank[v]) rank[u]++;
            }
        }
    }

    // 간선 정보 구조체
    static class Edge implements Comparable<Edge> {
        int u, v, cost;

        Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    static int kruskal(List<Edge> edges) {
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(vertexNum);
        int usedEdgeNum = 0, unionCount = 0;

        for (Edge edge : edges) {
            if (ds.find(edge.u) != ds.find(edge.v)) {
                ds.union(edge.u, edge.v);
                usedEdgeNum += edge.cost;
                unionCount++;
                if (unionCount == vertexNum - 1) break;
            }
        }
        return usedEdgeNum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            vertexNum = Integer.parseInt(st.nextToken());
            edgeNum = Integer.parseInt(st.nextToken());
            blueNum = Integer.parseInt(st.nextToken());

            if (vertexNum == 0 && edgeNum == 0 && blueNum == 0) break;

            List<Edge> minBlueEdges = new ArrayList<>();
            List<Edge> maxBlueEdges = new ArrayList<>();

            for (int i = 0; i < edgeNum; i++) {
                st = new StringTokenizer(br.readLine());
                char color = st.nextToken().charAt(0);
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                if (color == 'B') {
                    minBlueEdges.add(new Edge(u, v, 1)); // 파란 간선의 비용 1
                    maxBlueEdges.add(new Edge(u, v, 0)); // 파란 간선의 비용 0
                } else {
                    minBlueEdges.add(new Edge(u, v, 0)); // 빨간 간선의 비용 0
                    maxBlueEdges.add(new Edge(u, v, 1)); // 빨간 간선의 비용 1
                }
            }

            // 최소 파란 간선 개수
            int minBlue = kruskal(minBlueEdges);

            // 최대 파란 간선 개수
            int maxBlue = (vertexNum - 1) - kruskal(maxBlueEdges);

            // 결과 판단
            if (minBlue <= blueNum && blueNum <= maxBlue) {
                result.append("1\n");
            } else {
                result.append("0\n");
            }
        }

        System.out.print(result);
    }
}
