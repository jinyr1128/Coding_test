import java.io.*;
import java.util.*;

/**
 * 프로그래밍 대결 - Java 8 변환 버전
 * 로직은 주어진 C++ 코드(2D residual, SPFA 기반 MCMF)를 그대로 따릅니다.
 */
public class Main {
    static final int NODE = 602;            // 0..601 사용
    static final int INF  = 1000000007;

    static int source = 0, sink = 601;
    static int[][] cap = new int[NODE][NODE];
    static int[][] cost = new int[NODE][NODE];
    static ArrayList<Integer>[] adj = new ArrayList[NODE];

    static int[] parent = new int[NODE];
    static int[] dist = new int[NODE];
    static boolean[] inq = new boolean[NODE];

    static int[] A = new int[301];
    static int[] H = new int[301];

    static void addEdge(int u, int v, int c, int w) {
        if (cap[u][v] == 0 && cap[v][u] == 0) { // 처음 연결 시 인접리스트에 추가
            adj[u].add(v);
            adj[v].add(u);
        }
        cap[u][v] += c;
        cost[u][v] += w;
        cost[v][u] -= w;
    }

    static long[] mcmf() { // [maxFlow, minCost]
        long totalFlow = 0, totalCost = 0;

        while (true) {
            Arrays.fill(parent, -1);
            Arrays.fill(dist, INF);
            Arrays.fill(inq, false);

            Deque<Integer> q = new ArrayDeque<>();
            dist[source] = 0;
            q.add(source);
            inq[source] = true;

            while (!q.isEmpty()) {
                int u = q.poll();
                inq[u] = false;
                for (int v : adj[u]) {
                    if (cap[u][v] > 0 && dist[u] + cost[u][v] < dist[v]) {
                        dist[v] = dist[u] + cost[u][v];
                        parent[v] = u;
                        if (!inq[v]) {
                            q.add(v);
                            inq[v] = true;
                        }
                    }
                }
            }

            if (parent[sink] == -1) break;

            int add = INF;
            for (int v = sink; v != source; v = parent[v]) {
                add = Math.min(add, cap[parent[v]][v]);
            }
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                cap[u][v] -= add;
                cap[v][u] += add;
                totalCost += 1L * cost[u][v] * add;
            }
            totalFlow += add;
        }

        return new long[]{totalFlow, totalCost};
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < NODE; i++) adj[i] = new ArrayList<>();

        int N = Integer.parseInt(br.readLine().trim());

        // A 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxA = -1, maxIdx = -1;
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            if (A[i] > maxA) {
                maxA = A[i];
                maxIdx = i;
            }
        }

        // H 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        // source -> 왼쪽(i)
        for (int i = 1; i <= N; i++) {
            addEdge(source, i, 1, 0);
        }

        // 왼(i) -> 오(j+300) (A가 낮은 쪽에서 높은 쪽으로)
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (A[i] < A[j]) {
                    int w = -((A[i] ^ A[j]) - H[i] - H[j]);
                    addEdge(i, j + 300, 1, w);
                } else {
                    int w = -((A[j] ^ A[i]) - H[j] - H[i]);
                    addEdge(j, i + 300, 1, w);
                }
            }
        }

        // L 입력: 오른쪽(n+300) -> sink
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int L = Integer.parseInt(st.nextToken());
            if (i == maxIdx) {
                addEdge(i + 300, sink, L, 0);
            } else {
                addEdge(i + 300, sink, L - 1, 0);
            }
        }

        long[] res = mcmf();
        long minCost = res[1]; // 비용은 음수(=가치 최대화용)로 쌓였음
        System.out.println(-minCost);
    }
}
