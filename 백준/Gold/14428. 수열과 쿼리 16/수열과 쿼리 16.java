import java.io.*;
import java.util.*;

class Main {
    static class Node {
        int num = Integer.MAX_VALUE, idx = Integer.MAX_VALUE;
    }

    static int n, m;
    static int[] arr;
    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        tree = new Node[4 * n];
        for (int i = 0; i < 4 * n; i++) {
            tree[i] = new Node();
        }

        init(0, n - 1, 1);

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (type == 1) {
                update(0, n - 1, 1, x - 1, y);
            } else {
                pw.println(query(0, n - 1, 1, x - 1, y - 1).idx + 1);
            }
        }
        pw.flush();
    }

    static Node init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = new Node() {{
                num = arr[start];
                idx = start;
            }};
        }
        int mid = (start + end) / 2;
        Node left = init(start, mid, node * 2);
        Node right = init(mid + 1, end, node * 2 + 1);
        return tree[node] = min(left, right);
    }

    static Node query(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return new Node();
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        Node leftNode = query(start, mid, node * 2, left, right);
        Node rightNode = query(mid + 1, end, node * 2 + 1, left, right);
        return min(leftNode, rightNode);
    }

    static Node update(int start, int end, int node, int index, int value) {
        if (index < start || index > end) return tree[node];
        if (start == end) {
            return tree[node] = new Node() {{
                num = value;
                idx = index;
            }};
        }
        int mid = (start + end) / 2;
        Node left = update(start, mid, node * 2, index, value);
        Node right = update(mid + 1, end, node * 2 + 1, index, value);
        return tree[node] = min(left, right);
    }

    static Node min(Node a, Node b) {
        if (a.num == b.num) {
            return a.idx < b.idx ? a : b;
        }
        return a.num < b.num ? a : b;
    }
}
