import java.io.*;
import java.util.*;

public class Main {

    static class SegmentTree {
        long[] tree, lazy;
        int size;

        SegmentTree(int n) {
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2 * size];
            lazy = new long[2 * size];
        }

        // Lazy propagation
        private void propagate(int node, int nodeLeft, int nodeRight) {
            if (lazy[node] != 0) {
                tree[node] += lazy[node] * (nodeRight - nodeLeft + 1);
                if (nodeLeft != nodeRight) {
                    lazy[2 * node] += lazy[node];
                    lazy[2 * node + 1] += lazy[node];
                }
                lazy[node] = 0;
            }
        }

        // Update a range with a specific value
        public void update(int left, int right, int value) {
            update(left, right, value, 1, 0, size - 1);
        }

        private void update(int left, int right, int value, int node, int nodeLeft, int nodeRight) {
            propagate(node, nodeLeft, nodeRight);
            if (right < nodeLeft || nodeRight < left) return;
            if (left <= nodeLeft && nodeRight <= right) {
                lazy[node] += value;
                propagate(node, nodeLeft, nodeRight);
                return;
            }
            int mid = (nodeLeft + nodeRight) / 2;
            update(left, right, value, 2 * node, nodeLeft, mid);
            update(left, right, value, 2 * node + 1, mid + 1, nodeRight);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }

        // Query the sum of a range
        public long query(int left, int right) {
            return query(left, right, 1, 0, size - 1);
        }

        private long query(int left, int right, int node, int nodeLeft, int nodeRight) {
            propagate(node, nodeLeft, nodeRight);
            if (right < nodeLeft || nodeRight < left) return 0;
            if (left <= nodeLeft && nodeRight <= right) return tree[node];
            int mid = (nodeLeft + nodeRight) / 2;
            return query(left, right, 2 * node, nodeLeft, mid)
                    + query(left, right, 2 * node + 1, mid + 1, nodeRight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // Input the number of points
        int n = Integer.parseInt(br.readLine());
        long[] initialStars = new long[n + 1];

        // Input the initial number of stars
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            initialStars[i] = Long.parseLong(st.nextToken());
        }

        SegmentTree segTree = new SegmentTree(n);

        // Build the difference array
        for (int i = 1; i <= n; i++) {
            long diff = initialStars[i] - (i > 1 ? initialStars[i - 1] : 0);
            segTree.update(i, i, (int) diff);
        }

        // Input the number of queries
        int q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) { // Update range
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                segTree.update(l, r, 1);
                if (r + 1 <= n) {
                    segTree.update(r + 1, r + 1, -(r - l + 1));
                }
            } else if (type == 2) { // Query sum
                int x = Integer.parseInt(st.nextToken());
                bw.write(segTree.query(1, x) + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
