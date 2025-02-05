import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_N = 100000;
    static final int SQRT = 316;
    static int n, k, q;
    static int[] arr = new int[MAX_N + 1];
    static Query[] queries;
    static TreeSet<Integer>[] positions;
    static int[] dcnt = new int[MAX_N + 1], sqcnt = new int[(MAX_N / SQRT) + 10], ans;

    static class Query implements Comparable<Query> {
        int l, r, index;
        Query(int l, int r, int index) {
            this.l = l;
            this.r = r;
            this.index = index;
        }
        @Override
        public int compareTo(Query other) {
            if (l / SQRT != other.l / SQRT) return Integer.compare(l, other.l);
            return Integer.compare(r, other.r);
        }
    }

    static void remove(int idx) {
        int num = arr[idx];
        if (positions[num].size() >= 2) {
            int diff = positions[num].last() - positions[num].first();
            dcnt[diff]--;
            sqcnt[diff / SQRT]--;
        }
        positions[num].remove(idx);
        if (positions[num].size() >= 2) {
            int diff = positions[num].last() - positions[num].first();
            dcnt[diff]++;
            sqcnt[diff / SQRT]++;
        }
    }

    static void add(int idx) {
        int num = arr[idx];
        if (positions[num].size() >= 2) {
            int diff = positions[num].last() - positions[num].first();
            dcnt[diff]--;
            sqcnt[diff / SQRT]--;
        }
        positions[num].add(idx);
        if (positions[num].size() >= 2) {
            int diff = positions[num].last() - positions[num].first();
            dcnt[diff]++;
            sqcnt[diff / SQRT]++;
        }
    }

    static int getMaxDistance() {
        for (int i = sqcnt.length - 1; i >= 0; i--) {
            if (sqcnt[i] > 0) {
                for (int j = SQRT - 1; j >= 0; j--) {
                    int diff = i * SQRT + j;
                    if (diff < dcnt.length && dcnt[diff] > 0) return diff;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        q = Integer.parseInt(br.readLine());
        queries = new Query[q];
        ans = new int[q];
        positions = new TreeSet[k + 1];
        for (int i = 1; i <= k; i++) positions[i] = new TreeSet<>();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            queries[i] = new Query(l, r, i);
        }

        Arrays.sort(queries);
        int l = queries[0].l, r = queries[0].r;
        for (int i = l; i <= r; i++) add(i);
        ans[queries[0].index] = getMaxDistance();

        for (int i = 1; i < q; i++) {
            while (queries[i].l < l) add(--l);
            while (r < queries[i].r) add(++r);
            while (l < queries[i].l) remove(l++);
            while (queries[i].r < r) remove(r--);
            ans[queries[i].index] = getMaxDistance();
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < q; i++) bw.write(ans[i] + "\n");
        bw.flush();
    }
}
