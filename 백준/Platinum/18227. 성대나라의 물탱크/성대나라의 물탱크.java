import java.io.*;
import java.util.*;

public class Main {
    static int N, C, nodeNum = 0;
    static List<Integer>[] treeGraph;
    static int[][] indexTree;
    static long[] segmentTree, depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st;

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 초기화
        treeGraph = new ArrayList[N + 1];
        indexTree = new int[N + 1][2];
        segmentTree = new long[4 * N];
        depth = new long[N + 1];

        for (int i = 1; i <= N; i++) treeGraph[i] = new ArrayList<>();

        // 트리 입력
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            treeGraph[u].add(v);
            treeGraph[v].add(u);
        }

        // 깊이와 오일러 경로 설정
        depth[C] = 1;
        buildTree(C);

        // 쿼리 처리
        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int city = Integer.parseInt(st.nextToken());

            if (type == 1) {
                update(1, 1, N, indexTree[city][0]);
            } else {
                long result = query(1, 1, N, indexTree[city][0], indexTree[city][1]) * depth[city];
                pw.println(result);
            }
        }

        pw.flush();
        pw.close();
    }

    // 트리 빌드 (DFS를 이용한 오일러 경로 트릭)
    static void buildTree(int current) {
        indexTree[current][0] = ++nodeNum;
        for (int neighbor : treeGraph[current]) {
            if (depth[neighbor] == 0) {
                depth[neighbor] = depth[current] + 1;
                buildTree(neighbor);
            }
        }
        indexTree[current][1] = nodeNum;
    }

    // 세그먼트 트리 업데이트
    static void update(int node, int start, int end, int pos) {
        if (pos < start || pos > end) return;
        segmentTree[node]++;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(node * 2, start, mid, pos);
        update(node * 2 + 1, mid + 1, end, pos);
    }

    // 세그먼트 트리 쿼리
    static long query(int node, int start, int end, int l, int r) {
        if (r < start || l > end) return 0;
        if (l <= start && end <= r) return segmentTree[node];
        int mid = (start + end) / 2;
        return query(node * 2, start, mid, l, r) + query(node * 2 + 1, mid + 1, end, l, r);
    }
}
