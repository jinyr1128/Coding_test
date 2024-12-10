import java.io.*;
import java.util.*;

public class Main {

    static final int MAXN = 100000;
    static int N, Q, R, H;
    static int[] level, size;
    static int[][] parent;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Case #").append(t).append(":").append('\n');

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken()) - 1;

            // 그래프 초기화
            graph = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                graph[a].add(b);
                graph[b].add(a);
            }

            // 희소 배열 초기화
            H = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
            parent = new int[N][H];
            level = new int[N];
            size = new int[N];

            dfs(0, -1);

            // 희소 배열 채우기
            for (int j = 1; j < H; j++) {
                for (int i = 0; i < N; i++) {
                    if (parent[i][j - 1] != -1) {
                        parent[i][j] = parent[parent[i][j - 1]][j - 1];
                    }
                }
            }

            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int u = Integer.parseInt(st.nextToken()) - 1;

                if (s == 0) {
                    R = u; // 수도 변경
                } else {
                    if (R == u) {
                        sb.append(N).append('\n'); // 모든 노드가 수도를 거친다.
                    } else {
                        int l = lca(R, u);
                        if (l == u) {
                            // 수도로 가는 경로 중에서 U를 포함하는 경로 계산
                            int diff = level[R] - level[u] - 1;
                            int r = R;
                            for (int k = 0; diff > 0; k++) {
                                if ((diff & 1) == 1) {
                                    r = parent[r][k];
                                }
                                diff >>= 1;
                            }
                            sb.append(N - size[r]).append('\n');
                        } else {
                            sb.append(size[u]).append('\n'); // 서브트리 크기 출력
                        }
                    }
                }
            }
            out.print(sb);
        }
        out.flush();
    }

    // DFS로 레벨, 사이즈, 부모 초기화
    static void dfs(int node, int par) {
        size[node] = 1;
        parent[node][0] = par;
        for (int next : graph[node]) {
            if (next != par) {
                level[next] = level[node] + 1;
                dfs(next, node);
                size[node] += size[next];
            }
        }
    }

    // LCA 계산
    static int lca(int u, int v) {
        if (level[u] < level[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // 레벨 맞추기
        int diff = level[u] - level[v];
        for (int k = 0; diff > 0; k++) {
            if ((diff & 1) == 1) {
                u = parent[u][k];
            }
            diff >>= 1;
        }

        if (u == v) return u;

        // 공통 조상 찾기
        for (int k = H - 1; k >= 0; k--) {
            if (parent[u][k] != parent[v][k]) {
                u = parent[u][k];
                v = parent[v][k];
            }
        }
        return parent[u][0];
    }
}
