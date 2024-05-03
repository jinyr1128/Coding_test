import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int start, end, cost;

    Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.cost, o.cost);
    }
}

public class Main {
    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] planets = new int[N][4];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            planets[i][0] = Integer.parseInt(st.nextToken());
            planets[i][1] = Integer.parseInt(st.nextToken());
            planets[i][2] = Integer.parseInt(st.nextToken());
            planets[i][3] = i;
        }
        
        ArrayList<Edge> edges = new ArrayList<>();

        for (int j = 0; j < 3; j++) {
            int finalJ = j;
            Arrays.sort(planets, (a, b) -> Integer.compare(a[finalJ], b[finalJ]));
            for (int i = 1; i < N; i++) {
                int cost = Math.abs(planets[i][j] - planets[i - 1][j]);
                edges.add(new Edge(planets[i - 1][3], planets[i][3], cost));
            }
        }
        
        Collections.sort(edges);

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        
        int mstCost = 0;
        int count = 0;
        for (Edge edge : edges) {
            if (union(edge.start, edge.end)) {
                mstCost += edge.cost;
                if (++count == N - 1) break;
            }
        }
        
        System.out.println(mstCost);
    }
}
