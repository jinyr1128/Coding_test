import java.io.*;
import java.util.*;

class SegmentTree {
    int size;
    long[] tree;
    int[] collection;

    public SegmentTree(int n, int m) {
        size = n + m;
        tree = new long[size * 4];
        collection = new int[n];
    }

    // 세그먼트 트리 초기화
    public long init(int node, int m, int start, int end) {
        if (start == end) {
            if (start >= m) {
                collection[start - m] = start;
                tree[node] = 1;
            }
            return tree[node];
        }

        int mid = (start + end) / 2;
        return tree[node] = init(node * 2, m, start, mid) + init(node * 2 + 1, m, mid + 1, end);
    }

    public void init(int m) {
        init(1, m, 0, size - 1);
    }

    // 구간 합 구하기
    public long query(int node, int start, int end, int nodeLeft, int nodeRight) {
        if (end < nodeLeft || nodeRight < start)
            return 0;
        if (start <= nodeLeft && nodeRight <= end)
            return tree[node];

        int mid = (nodeLeft + nodeRight) / 2;
        return query(node * 2, start, end, nodeLeft, mid) + query(node * 2 + 1, start, end, mid + 1, nodeRight);
    }

    public long query(int start, int end) {
        return query(1, start, collection[end] - 1, 0, size - 1);
    }

    // 세그먼트 트리 업데이트
    public long update(int idx, int node, int val, int nodeLeft, int nodeRight) {
        if (idx < nodeLeft || nodeRight < idx)
            return tree[node];
        if (nodeLeft == nodeRight)
            return tree[node] = val;

        int mid = (nodeLeft + nodeRight) / 2;
        return tree[node] = update(idx, node * 2, val, nodeLeft, mid) + update(idx, node * 2 + 1, val, mid + 1, nodeRight);
    }

    public long update(int idx, int val) {
        return update(collection[idx], 1, val, 0, size - 1);
    }

    // 해당 DVD의 인덱스 업데이트
    public void change(int idx, int val) {
        collection[idx] = val;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test_case = Integer.parseInt(br.readLine());

        for (int t = 0; t < test_case; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            SegmentTree segTree = new SegmentTree(n, m);
            segTree.init(m);

            int idx = m - 1;
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken()) - 1;
                bw.write(segTree.query(0, num) + " ");

                segTree.update(num, 0); // 해당 DVD를 꺼내고
                segTree.change(num, idx); // DVD의 위치를 업데이트
                segTree.update(num, 1); // 세그먼트 트리 업데이트

                idx--; // 빈 공간 이동
            }
            bw.newLine();
        }

        br.close();
        bw.flush();
        bw.close();
    }
}

