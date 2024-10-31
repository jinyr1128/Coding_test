import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] segTree;
    static long[] lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        segTree = new long[N * 4];
        lazy = new long[N * 4];

        // 초기 배열 입력을 통해 세그먼트 트리 초기화
        for (int i = 1; i <= N; i++) {
            long value = Long.parseLong(br.readLine().trim());
            update(1, 1, N, i, i, value);
        }

        int Q = M + K;
        StringBuilder result = new StringBuilder();

        // 질의 처리
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if (a == 1) {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long d = Long.parseLong(st.nextToken());
                update(1, 1, N, b, c, d);
            } else if (a == 2) {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                result.append(query(1, 1, N, b, c)).append("\n");
            }
        }
        System.out.print(result);
    }

    // 구간 업데이트 메서드 (지연 갱신 포함)
    static void update(int node, int start, int end, int left, int right, long diff) {
        propagate(node, start, end);

        if (right < start || end < left) return;

        if (left <= start && end <= right) {
            segTree[node] += (end - start + 1) * diff;
            if (start != end) {
                lazy[node * 2] += diff;
                lazy[node * 2 + 1] += diff;
            }
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, left, right, diff);
        update(node * 2 + 1, mid + 1, end, left, right, diff);

        segTree[node] = segTree[node * 2] + segTree[node * 2 + 1];
    }

    // 구간 합 쿼리 메서드 (지연 갱신 포함)
    static long query(int node, int start, int end, int left, int right) {
        propagate(node, start, end);

        if (right < start || end < left) return 0;

        if (left <= start && end <= right) return segTree[node];

        int mid = (start + end) / 2;
        return query(node * 2, start, mid, left, right) 
             + query(node * 2 + 1, mid + 1, end, left, right);
    }

    // 지연 갱신 적용 메서드
    static void propagate(int node, int start, int end) {
        if (lazy[node] != 0) {
            segTree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }
}
