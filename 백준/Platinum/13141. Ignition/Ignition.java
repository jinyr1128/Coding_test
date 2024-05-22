import java.util.*;
import java.io.*;

public class Main {
    static final int INF = 987654321;
    static int vertexNum, edgeNum;
    static int[][] adj;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        vertexNum = Integer.parseInt(st.nextToken());
        edgeNum = Integer.parseInt(st.nextToken());

        adj = new int[vertexNum + 1][vertexNum + 1];
        dist = new int[vertexNum + 1][vertexNum + 1];

        for (int i = 1; i <= vertexNum; i++) {
            Arrays.fill(adj[i], -1);
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            if (adj[S][E] == -1 || adj[S][E] < L) {
                adj[S][E] = L;
                adj[E][S] = L;
            }
            if (dist[S][E] == INF || L < dist[S][E]) {
                dist[S][E] = L;
                dist[E][S] = L;
            }
        }

        floyd();

        System.out.printf("%.1f\n", burnGraph());
    }

    static void floyd() {
        for (int k = 1; k <= vertexNum; k++) {
            for (int i = 1; i <= vertexNum; i++) {
                for (int j = 1; j <= vertexNum; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    static double burnGraph() {
        double shortestTime = INF;

        for (int start = 1; start <= vertexNum; start++) {
            double longestTime = 0;

            for (int from = 1; from <= vertexNum; from++) {
                for (int to = 1; to <= vertexNum; to++) {
                    int edgeLen = adj[from][to];

                    if (edgeLen != -1) {
                        double remainLen = edgeLen - (dist[start][to] - dist[start][from]);

                        if (remainLen > 0) {
                            double spentTime = (remainLen / 2) + dist[start][to];
                            longestTime = Math.max(spentTime, longestTime);
                        }
                    }
                }
            }

            shortestTime = Math.min(longestTime, shortestTime);
        }

        return shortestTime;
    }
}
