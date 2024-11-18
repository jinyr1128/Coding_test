import java.io.*;
import java.util.*;

public class Main {
    static int N, M, k;
    static int[] A;
    static List<Integer>[] segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());

        // 머지 소트 트리 초기화
        segmentTree = new ArrayList[4 * N];
        for (int i = 0; i < 4 * N; i++) {
            segmentTree[i] = new ArrayList<>();
        }
        build(1, N, 1);

        // 쿼리 처리
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            int result = query(1, N, 1, l, r);
            sb.append(result).append("\n");
        }

        // 결과 출력
        System.out.print(sb.toString());
    }

    // 머지 소트 트리 빌드
    static void build(int start, int end, int node) {
        if (start == end) {
            segmentTree[node].add(A[start]);
        } else {
            int mid = (start + end) / 2;
            build(start, mid, node * 2);
            build(mid + 1, end, node * 2 + 1);

            segmentTree[node] = merge(segmentTree[node * 2], segmentTree[node * 2 + 1]);
        }
    }

    // 두 리스트 병합
    static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }

        while (i < left.size()) merged.add(left.get(i++));
        while (j < right.size()) merged.add(right.get(j++));

        return merged;
    }

    // 쿼리 처리
    static int query(int start, int end, int node, int left, int right) {
        if (right < start || end < left) {
            return 0;
        }

        if (left <= start && end <= right) {
            return segmentTree[node].size() - upperBound(segmentTree[node], k);
        }

        int mid = (start + end) / 2;
        return query(start, mid, node * 2, left, right) +
               query(mid + 1, end, node * 2 + 1, left, right);
    }

    // upper_bound 구현
    static int upperBound(List<Integer> list, int value) {
        int low = 0, high = list.size();

        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid) <= value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}
