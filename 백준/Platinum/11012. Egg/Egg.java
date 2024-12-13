import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_SIZE = 100005;
    static Node[] trees;

    static class Node {
        long value;
        Node left, right;

        Node() {
            this.value = 0;
            this.left = this.right = null;
        }

        Node(Node left, Node right, long value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        Node insert(int l, int r, int pos, long val) {
            if (l <= pos && pos <= r) {
                if (l == r) {
                    return new Node(null, null, this.value + val);
                }
                int mid = (l + r) / 2;
                Node leftChild = this.left != null ? this.left : new Node();
                Node rightChild = this.right != null ? this.right : new Node();
                return new Node(
                        leftChild.insert(l, mid, pos, val),
                        rightChild.insert(mid + 1, r, pos, val),
                        this.value + val
                );
            }
            return this;
        }
    }

    static long query(Node node, int s, int e, int l, int r) {
        if (node == null || e < l || r < s) {
            return 0;
        }
        if (l <= s && e <= r) {
            return node.value;
        }
        int mid = (s + e) / 2;
        return query(node.left, s, mid, l, r) + query(node.right, mid + 1, e, l, r);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            List<List<Integer>> xpos = new ArrayList<>();
            for (int i = 0; i <= MAX_SIZE; i++) {
                xpos.add(new ArrayList<>());
            }

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) + 1;
                int y = Integer.parseInt(st.nextToken()) + 1;
                xpos.get(y).add(x);
            }

            trees = new Node[MAX_SIZE + 2];
            trees[0] = new Node();
            for (int y = 1; y <= MAX_SIZE; y++) {
                trees[y] = trees[y - 1];
                for (int x : xpos.get(y)) {
                    trees[y] = trees[y].insert(1, MAX_SIZE, x, 1);
                }
            }

            long totalEggs = 0;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken()) + 1;
                int r = Integer.parseInt(st.nextToken()) + 1;
                int b = Integer.parseInt(st.nextToken()) + 1;
                int t = Integer.parseInt(st.nextToken()) + 1;

                totalEggs += query(trees[t], 1, MAX_SIZE, l, r);
                totalEggs -= query(trees[b - 1], 1, MAX_SIZE, l, r);
            }
            output.append(totalEggs).append("\n");
        }
        System.out.print(output);
    }
}
