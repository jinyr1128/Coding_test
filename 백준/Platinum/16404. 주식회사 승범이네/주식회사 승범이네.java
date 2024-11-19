import java.io.*;
import java.util.*;

public class Main {
    static int[] tree, lazy, start, end;
    static int ett = 0; // Euler Tour Trick에서 사용할 변수
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st;

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 세그먼트 트리 및 lazy 배열 초기화
        tree = new int[4 * n];
        lazy = new int[4 * n];

        // 그래프 및 오일러 경로 배열 초기화
        start = new int[n + 1];
        end = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        // 트리 구조 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent != -1) graph[parent].add(i);
        }

        // 오일러 경로 구성
        dfs(1);

        // 쿼리 처리
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int value = Integer.parseInt(st.nextToken());
                update(1, n, 1, start[node], end[node], value);
            } else {
                pw.println(query(1, n, 1, start[node]));
            }
        }

        pw.flush();
        pw.close();
    }

    // 오일러 경로 구성
    static void dfs(int node) {
        start[node] = ++ett;
        for (int child : graph[node]) {
            dfs(child);
        }
        end[node] = ett;
    }

    // Lazy propagation 처리
    static void applyLazy(int s, int e, int node) {
        if (lazy[node] != 0) {
            tree[node] += (e - s + 1) * lazy[node];
            if (s != e) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    // 세그먼트 트리 갱신
    static void update(int s, int e, int node, int l, int r, int value) {
        applyLazy(s, e, node);
        if (s > r || e < l) return; // 범위를 벗어남
        if (l <= s && e <= r) { // 범위에 포함됨
            tree[node] += (e - s + 1) * value;
            if (s != e) {
                lazy[node * 2] += value;
                lazy[node * 2 + 1] += value;
            }
            return;
        }
        int mid = (s + e) / 2;
        update(s, mid, node * 2, l, r, value);
        update(mid + 1, e, node * 2 + 1, l, r, value);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    // 세그먼트 트리 쿼리
    static int query(int s, int e, int node, int index) {
        applyLazy(s, e, node);
        if (s > index || e < index) return 0; // 범위를 벗어남
        if (s == e) return tree[node]; // 단일 범위
        int mid = (s + e) / 2;
        return query(s, mid, node * 2, index) + query(mid + 1, e, node * 2 + 1, index);
    }
}
