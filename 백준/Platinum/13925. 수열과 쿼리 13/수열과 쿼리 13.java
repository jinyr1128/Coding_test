import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1000000007;
    static int N, M;
    static long[] arr, tree, mulLazy, addLazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        tree = new long[4 * N];
        mulLazy = new long[4 * N];
        addLazy = new long[4 * N];
        Arrays.fill(mulLazy, 1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        init(1, 0, N - 1);

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            if (order == 1) {
                long v = Long.parseLong(st.nextToken());
                updateRange(1, 0, N - 1, x, y, 1, v);
            } else if (order == 2) {
                long v = Long.parseLong(st.nextToken());
                updateRange(1, 0, N - 1, x, y, v, 0);
            } else if (order == 3) {
                long v = Long.parseLong(st.nextToken());
                updateRange(1, 0, N - 1, x, y, 0, v);
            } else if (order == 4) {
                pw.println(query(1, 0, N - 1, x, y));
            }
        }
        pw.flush();
    }

    static long init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = (init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end)) % MOD;
    }

    static void updateLazy(int node, int start, int end) {
        if (mulLazy[node] == 1 && addLazy[node] == 0) return;

        tree[node] = (tree[node] * mulLazy[node] % MOD + addLazy[node] * (end - start + 1) % MOD) % MOD;

        if (start != end) {
            for (int child : new int[]{node * 2, node * 2 + 1}) {
                mulLazy[child] = mulLazy[child] * mulLazy[node] % MOD;
                addLazy[child] = (addLazy[child] * mulLazy[node] % MOD + addLazy[node]) % MOD;
            }
        }

        mulLazy[node] = 1;
        addLazy[node] = 0;
    }

    static void updateRange(int node, int start, int end, int l, int r, long mul, long add) {
        updateLazy(node, start, end);

        if (r < start || end < l) return;

        if (l <= start && end <= r) {
            mulLazy[node] = mulLazy[node] * mul % MOD;
            addLazy[node] = (addLazy[node] * mul % MOD + add) % MOD;
            updateLazy(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        updateRange(node * 2, start, mid, l, r, mul, add);
        updateRange(node * 2 + 1, mid + 1, end, l, r, mul, add);
        tree[node] = (tree[node * 2] + tree[node * 2 + 1]) % MOD;
    }

    static long query(int node, int start, int end, int l, int r) {
        updateLazy(node, start, end);

        if (r < start || end < l) return 0;

        if (l <= start && end <= r) return tree[node];

        int mid = (start + end) / 2;
        return (query(node * 2, start, mid, l, r) + query(node * 2 + 1, mid + 1, end, l, r)) % MOD;
    }
}
