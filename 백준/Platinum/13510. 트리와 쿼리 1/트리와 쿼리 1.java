import java.io.*;
import java.util.*;

public class Main {
    static final int MAXN = 100001;

    static int N, Q, cnt, len;
    static List<Integer>[] adj;
    static int[] top, pa, sz, idx;
    static int[] eu, ev, ew;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[MAXN];
        for (int i = 0; i < MAXN; i++) adj[i] = new ArrayList<>();

        top = new int[MAXN];
        pa = new int[MAXN];
        sz = new int[MAXN];
        idx = new int[MAXN];
        eu = new int[MAXN];
        ev = new int[MAXN];
        ew = new int[MAXN];

        len = (int) Math.pow(2, Math.ceil(Math.log(N) / Math.log(2)));
        tree = new int[len * 2];

        for (int i = 1; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            eu[i] = Integer.parseInt(st.nextToken()) - 1;
            ev[i] = Integer.parseInt(st.nextToken()) - 1;
            ew[i] = Integer.parseInt(st.nextToken());
            adj[eu[i]].add(ev[i]);
            adj[ev[i]].add(eu[i]);
        }

        dfs(0);
        hld(0);
        init();

        Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (q == 1) {
                update(idx[eu[u]], v);
            } else {
                pw.println(solve(u - 1, v - 1));
            }
        }
        pw.flush();
    }

    static int dfs(int here) {
        sz[here] = 1;
        for (int i = 0; i < adj[here].size(); i++) {
            int there = adj[here].get(i);
            if (pa[here] != there) {
                pa[there] = here;
                sz[here] += dfs(there);
                int h = adj[here].get(0);
                if (h == pa[here] || sz[h] < sz[there]) {
                    Collections.swap(adj[here], 0, i);
                }
            }
        }
        return sz[here];
    }

    static void hld(int here) {
        idx[here] = cnt++;
        for (int there : adj[here]) {
            if (there != pa[here]) {
                top[there] = (there == adj[here].get(0)) ? top[here] : there;
                hld(there);
            }
        }
    }

    static void init() {
        for (int i = 1; i < N; ++i) {
            if (pa[ev[i]] == eu[i]) {
                int temp = ev[i];
                ev[i] = eu[i];
                eu[i] = temp;
            }
            tree[len + idx[eu[i]]] = ew[i];
        }

        for (int i = len - 1; i > 0; --i) {
            tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
        }
    }

    static void update(int i, int v) {
        i += len;
        tree[i] = v;
        while (i > 1) {
            i /= 2;
            tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
        }
    }

    static int query(int l, int r) {
        l += len;
        r += len;
        int ret = 0;
        while (l <= r) {
            if ((l & 1) == 1) ret = Math.max(ret, tree[l++]);
            if ((r & 1) == 0) ret = Math.max(ret, tree[r--]);
            l /= 2;
            r /= 2;
        }
        return ret;
    }

    static int solve(int u, int v) {
        int ret = 0;
        while (top[u] != top[v]) {
            if (sz[top[u]] < sz[top[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }
            ret = Math.max(ret, query(idx[top[v]], idx[v]));
            v = pa[top[v]];
        }
        if (idx[u] > idx[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        ret = Math.max(ret, query(idx[u] + 1, idx[v]));
        return ret;
    }
}
