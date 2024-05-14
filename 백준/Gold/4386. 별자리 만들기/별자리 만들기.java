import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int u, v;
        double weight;

        Edge(int u, int v, double weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.weight, other.weight);
        }
    }

    static int[] parent;
    static List<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        double[][] stars = new double[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }

        edges = new ArrayList<>();
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double distance = getDistance(stars[i], stars[j]);
                edges.add(new Edge(i, j, distance));
            }
        }

        Collections.sort(edges);

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        double totalWeight = 0;
        int edgeCount = 0;
        for (Edge edge : edges) {
            if (find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
                totalWeight += edge.weight;
                edgeCount++;
                if (edgeCount == n - 1) break;
            }
        }

        System.out.printf("%.2f\n", totalWeight);
    }

    private static double getDistance(double[] star1, double[] star2) {
        return Math.sqrt(Math.pow(star1[0] - star2[0], 2) + Math.pow(star1[1] - star2[1], 2));
    }

    private static int find(int u) {
        if (parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) parent[u] = v;
    }
}
