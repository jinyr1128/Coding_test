import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] array;
    static long[] segTree;
    static long[] lazy;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine().trim());
        array = new long[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 1; i <= N; i++) {
            array[i] = Long.parseLong(st.nextToken());
        }

        // 세그먼트 트리와 lazy 배열 초기화
        segTree = new long[4 * N];
        lazy = new long[4 * N];
        initializeSegTree(1, 1, N);

        int M = Integer.parseInt(br.readLine().trim());
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            
            if (op == 1) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                updateSegTree(1, 1, N, left, right, k);
            } else if (op == 2) {
                int x = Integer.parseInt(st.nextToken());
                output.append(querySegTree(1, 1, N, x, x)).append("\n");
            }
        }
        
        System.out.print(output);
    }

    // 세그먼트 트리 초기화
    static long initializeSegTree(int index, int start, int end) {
        if (start == end) {
            segTree[index] = array[start];
            return segTree[index];
        }
        
        int mid = (start + end) / 2;
        segTree[index] = initializeSegTree(index * 2, start, mid) 
                       + initializeSegTree(index * 2 + 1, mid + 1, end);
        return segTree[index];
    }

    // Lazy propagation 적용
    static void propagateSegTree(int index, int start, int end) {
        if (lazy[index] != 0) {
            segTree[index] += (end - start + 1) * lazy[index];
            
            if (start != end) {
                lazy[index * 2] += lazy[index];
                lazy[index * 2 + 1] += lazy[index];
            }
            
            lazy[index] = 0;
        }
    }

    // 구간 업데이트
    static void updateSegTree(int index, int start, int end, int left, int right, int value) {
        propagateSegTree(index, start, end);
        
        if (right < start || end < left) {
            return;
        }
        
        if (left <= start && end <= right) {
            lazy[index] += value;
            propagateSegTree(index, start, end);
            return;
        }
        
        int mid = (start + end) / 2;
        updateSegTree(index * 2, start, mid, left, right, value);
        updateSegTree(index * 2 + 1, mid + 1, end, left, right, value);
        
        segTree[index] = segTree[index * 2] + segTree[index * 2 + 1];
    }

    // 구간 쿼리
    static long querySegTree(int index, int start, int end, int left, int right) {
        propagateSegTree(index, start, end);
        
        if (right < start || end < left) {
            return 0;
        }
        
        if (left <= start && end <= right) {
            return segTree[index];
        }
        
        int mid = (start + end) / 2;
        return querySegTree(index * 2, start, mid, left, right)
             + querySegTree(index * 2 + 1, mid + 1, end, left, right);
    }
}
