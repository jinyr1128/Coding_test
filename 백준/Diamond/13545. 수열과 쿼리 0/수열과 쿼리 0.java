import java.io.*;
import java.util.*;

public class Main {
    static final int SQRT = 400;
    static final int MAX_N = 100000;
    static int n, q;
    static int[] arr = new int[MAX_N + 1];
    static List<Query> queries = new ArrayList<>();
    static List<Integer>[] pos = new ArrayList[MAX_N * 2 + 1];
    static int[] count = new int[MAX_N + 1], bucket = new int[(MAX_N / SQRT) + 10], ans;

    static class Query implements Comparable<Query> {
        int s, e, index;
        Query(int s, int e, int index) {
            this.s = s;
            this.e = e;
            this.index = index;
        }
        @Override
        public int compareTo(Query other) {
            if (s / SQRT != other.s / SQRT) return Integer.compare(s, other.s);
            return Integer.compare(e, other.e);
        }
    }

    static void add(int x, int dir) {
        int current = 0;
        List<Integer> list = pos[arr[x]];
        if (!list.isEmpty()) {
            current = list.get(list.size() - 1) - list.get(0);
            count[current]--;
            bucket[current / SQRT]--;
        }
        if (dir == 0) list.add(0, x);
        else list.add(x);
        current = list.get(list.size() - 1) - list.get(0);
        count[current]++;
        bucket[current / SQRT]++;
    }

    static void remove(int x, int dir) {
        List<Integer> list = pos[arr[x]];
        int current = list.get(list.size() - 1) - list.get(0);
        count[current]--;
        bucket[current / SQRT]--;
        if (dir == 0) list.remove(0);
        else list.remove(list.size() - 1);
        if (!list.isEmpty()) {
            current = list.get(list.size() - 1) - list.get(0);
            count[current]++;
            bucket[current / SQRT]++;
        }
    }

    static int query() {
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] == 0) continue;
            for (int j = SQRT - 1; j >= 0; j--) {
                int index = i * SQRT + j;
                if (index < count.length && count[index] > 0) return index;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= n; i++) {
            arr[i] += arr[i - 1];
        }
        for (int i = 0; i <= n; i++) {
            arr[i] += MAX_N;
        }
        for (int i = 0; i < pos.length; i++) pos[i] = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        q = Integer.parseInt(st.nextToken());
        ans = new int[q];
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken());
            queries.add(new Query(s, e, i));
        }
        queries.sort(null);
        int s = queries.get(0).s, e = queries.get(0).e, x = queries.get(0).index;
        for (int i = s; i <= e; i++) add(i, 1);
        ans[x] = query();
        for (int i = 1; i < q; i++) {
            x = queries.get(i).index;
            while (queries.get(i).s < s) add(--s, 0);
            while (e < queries.get(i).e) add(++e, 1);
            while (s < queries.get(i).s) remove(s++, 0);
            while (queries.get(i).e < e) remove(e--, 1);
            ans[x] = query();
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < q; i++) bw.write(ans[i] + "\n");
        bw.flush();
    }
}
