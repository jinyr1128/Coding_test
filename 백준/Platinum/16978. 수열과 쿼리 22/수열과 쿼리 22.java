import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long[] segTree;
    static long[] result;
    static int N;
    static final int MAX = 100000;

    static class Query {
        int k, start, end, index;
        public Query(int k, int start, int end, int index) {
            this.k = k;
            this.start = start;
            this.end = end;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 수열의 크기와 수열 입력 처리
        N = Integer.parseInt(br.readLine());
        segTree = new long[4 * N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 1; i <= N; i++) {
            int value = Integer.parseInt(st.nextToken());
            updateSegTree(1, 1, N, i, value);
        }

        // 쿼리 개수와 쿼리 처리 준비
        int M = Integer.parseInt(br.readLine());
        List<int[]> updates = new ArrayList<>();
        List<Query> queries = new ArrayList<>();
        result = new long[M];
        int queryIndex = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) {
                int index = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                updates.add(new int[]{index, value});
            } else {
                int k = Integer.parseInt(st.nextToken());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                queries.add(new Query(k, start, end, queryIndex++));
            }
        }

        // 쿼리를 k에 따라 정렬하여 처리
        queries.sort(Comparator.comparingInt(q -> q.k));
        
        int updateIndex = 0;
        for (Query query : queries) {
            // 필요한 업데이트 쿼리를 모두 반영
            while (updateIndex < query.k) {
                int[] update = updates.get(updateIndex++);
                int index = update[0];
                int value = update[1];

                // 현재 index에 있는 값과 새로운 값의 차이를 구하고 적용
                long currentValue = querySegTree(1, 1, N, index, index);
                long diff = value - currentValue;
                updateSegTree(1, 1, N, index, diff);
            }

            // 구간 합 쿼리 수행 및 결과 저장
            result[query.index] = querySegTree(1, 1, N, query.start, query.end);
        }

        // 결과 출력
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < queryIndex; i++) {
            output.append(result[i]).append("\n");
        }
        System.out.print(output);
    }

    // 세그먼트 트리 업데이트
    static void updateSegTree(int node, int start, int end, int index, long diff) {
        if (index < start || index > end) {
            return;
        }
        segTree[node] += diff;
        if (start != end) {
            int mid = (start + end) / 2;
            updateSegTree(node * 2, start, mid, index, diff);
            updateSegTree(node * 2 + 1, mid + 1, end, index, diff);
        }
    }

    // 세그먼트 트리 구간 합 쿼리
    static long querySegTree(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return 0;
        }
        if (left <= start && end <= right) {
            return segTree[node];
        }
        int mid = (start + end) / 2;
        return querySegTree(node * 2, start, mid, left, right) + 
               querySegTree(node * 2 + 1, mid + 1, end, left, right);
    }
}
