import java.util.*;

public class Main {
    static class LazySegmentTree {
        long[] tree, lazy;
        int size;

        public LazySegmentTree(int n) {
            size = 1;
            while (size < n) size <<= 1;
            tree = new long[size << 1];
            lazy = new long[size << 1];
        }

        void propagate(int node, int nodeLeft, int nodeRight) {
            if (lazy[node] != 0) {
                tree[node] += (nodeRight - nodeLeft + 1) * lazy[node];
                if (nodeLeft != nodeRight) {
                    lazy[node * 2] += lazy[node];
                    lazy[node * 2 + 1] += lazy[node];
                }
                lazy[node] = 0;
            }
        }

        void update(int l, int r, long value) {
            update(1, 1, size, l, r, value);
        }

        void update(int node, int nodeLeft, int nodeRight, int l, int r, long value) {
            propagate(node, nodeLeft, nodeRight);
            if (r < nodeLeft || nodeRight < l) return;
            if (l <= nodeLeft && nodeRight <= r) {
                lazy[node] += value;
                propagate(node, nodeLeft, nodeRight);
                return;
            }
            int mid = (nodeLeft + nodeRight) >> 1;
            update(node * 2, nodeLeft, mid, l, r, value);
            update(node * 2 + 1, mid + 1, nodeRight, l, r, value);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        long query(int index) {
            return query(1, 1, size, index);
        }

        long query(int node, int nodeLeft, int nodeRight, int index) {
            propagate(node, nodeLeft, nodeRight);
            if (index < nodeLeft || nodeRight < index) return 0;
            if (nodeLeft == nodeRight) return tree[node];
            int mid = (nodeLeft + nodeRight) >> 1;
            return query(node * 2, nodeLeft, mid, index) + query(node * 2 + 1, mid + 1, nodeRight, index);
        }
    }

    static class SegmentTree {
        long[] tree;
        int size;

        public SegmentTree(int n) {
            size = 1;
            while (size < n) size <<= 1;
            tree = new long[size << 1];
        }

        void update(int index, long value) {
            index += size;
            tree[index] = value;
            while ((index >>= 1) > 0) {
                tree[index] = gcd(tree[index << 1], tree[index << 1 | 1]);
            }
        }

        long query(int l, int r) {
            l += size;
            r += size;
            long res = 0;
            while (l <= r) {
                if ((l & 1) == 1) res = gcd(res, tree[l++]);
                if ((r & 1) == 0) res = gcd(res, tree[r--]);
                l >>= 1;
                r >>= 1;
            }
            return res;
        }

        long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        LazySegmentTree lazyTree = new LazySegmentTree(n);
        SegmentTree segmentTree = new SegmentTree(n);

        for (int i = 1; i <= n; i++) {
            long value = sc.nextLong();
            lazyTree.update(i, i, value);
        }

        for (int i = 1; i < n; i++) {
            long diff = Math.abs(lazyTree.query(i) - lazyTree.query(i + 1));
            segmentTree.update(i, diff);
        }

        int q = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            int op = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (op == 0) {
                long result = segmentTree.query(a, b - 1);
                result = segmentTree.gcd(result, lazyTree.query(a));
                sb.append(result).append("\n");
            } else {
                lazyTree.update(a, b, op);
                if (a > 1) {
                    long diff = Math.abs(lazyTree.query(a - 1) - lazyTree.query(a));
                    segmentTree.update(a - 1, diff);
                }
                if (b < n) {
                    long diff = Math.abs(lazyTree.query(b) - lazyTree.query(b + 1));
                    segmentTree.update(b, diff);
                }
            }
        }
        System.out.print(sb);
    }
}
