import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static long[] arr;
    static long[] tree;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new long[n + 1];
        tree = new long[n * 4];

        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        buildTree(1, 1, n);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(1, 1, n, b, c);
            } else if (a == 2) {
                pw.println(query(1, 1, n, b, (int) c));
            }
        }
        pw.flush();
    }

    static long buildTree(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = (buildTree(node * 2, start, mid) * buildTree(node * 2 + 1, mid + 1, end)) % MOD;
    }

    static long query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return 1;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return (query(node * 2, start, mid, left, right) * query(node * 2 + 1, mid + 1, end, left, right)) % MOD;
    }

    static void update(int node, int start, int end, int idx, long value) {
        if (idx < start || idx > end) {
            return;
        }
        if (start == end) {
            tree[node] = value;
            return;
        }
        int mid = (start + end) / 2;
        update(node * 2, start, mid, idx, value);
        update(node * 2 + 1, mid + 1, end, idx, value);
        tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD;
    }
}
