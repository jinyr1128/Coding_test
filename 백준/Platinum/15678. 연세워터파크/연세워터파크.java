import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N, D;
    static long[] K;
    static long[] tree;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        K = new long[N + 1];
        tree = new long[4 * N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            K[i] = Long.parseLong(st.nextToken());
        }
        
        long ans = K[1];
        update(1, 1, N, 1, K[1]);
        
        for (int i = 2; i <= N; i++) {
            long temp = Math.max(0, query(1, 1, N, Math.max(1, i - D), i - 1));
            ans = Math.max(ans, K[i] + temp);
            update(1, 1, N, i, K[i] + temp);
        }
        
        System.out.println(ans);
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
        tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
    }
    
    static long query(int node, int start, int end, int qs, int qe) {
        if (qs > end || qe < start) {
            return Long.MIN_VALUE;
        }
        if (qs <= start && end <= qe) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return Math.max(query(node * 2, start, mid, qs, qe), query(node * 2 + 1, mid + 1, end, qs, qe));
    }
}
