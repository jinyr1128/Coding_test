import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 200001;

    static int N, Q;
    static List<Integer>[] adj;
    static int[] subtreeSize, HLDNum, HLDHead, HLDDepth, HLDParent;
    static int[] tree;
    static int HLDCount = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        adj = new ArrayList[MAX];
        for (int i = 0; i < MAX; i++) adj[i] = new ArrayList<>();

        subtreeSize = new int[MAX];
        HLDNum = new int[MAX];
        HLDHead = new int[MAX];
        HLDDepth = new int[MAX];
        HLDParent = new int[MAX];

        int treeHeight = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeSize = (1 << (treeHeight + 1));
        tree = new int[treeSize];
        Arrays.fill(tree, 1);

        for (int i = 1; i < N; i++) {
            int p = Integer.parseInt(br.readLine());
            adj[p].add(i + 1);
        }

        HLDHead[1] = 1;
        calcSubtreeSize(1);
        heavyLightDecomposition(1, 1);

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            boolean result = true;
            int B = b, C = c;

            if (HLDDepth[b] > HLDDepth[c]) {
                int temp = b;
                b = c;
                c = temp;
            }

            while (HLDDepth[b] < HLDDepth[c]) {
                result &= queryTree(1, 1, N, HLDNum[HLDHead[c]], HLDNum[c]) == 1;
                c = HLDParent[c];
            }

            while (HLDHead[b] != HLDHead[c]) {
                result &= queryTree(1, 1, N, HLDNum[HLDHead[b]], HLDNum[b]) == 1;
                result &= queryTree(1, 1, N, HLDNum[HLDHead[c]], HLDNum[c]) == 1;
                b = HLDParent[b];
                c = HLDParent[c];
            }

            if (HLDNum[b] > HLDNum[c]) {
                int temp = b;
                b = c;
                c = temp;
            }

            result &= queryTree(1, 1, N, HLDNum[b] + 1, HLDNum[c]) == 1;

            pw.println(result ? "YES" : "NO");

            if (d == 1) {
                if (result) updateTree(1, 1, N, HLDNum[B], 0);
                else updateTree(1, 1, N, HLDNum[C], 0);
            }
        }

        pw.flush();
    }

    static void calcSubtreeSize(int node) {
        subtreeSize[node] = 1;
        for (int i = 0; i < adj[node].size(); i++) {
            int child = adj[node].get(i);
            calcSubtreeSize(child);
            subtreeSize[node] += subtreeSize[child];
            if (subtreeSize[child] > subtreeSize[adj[node].get(0)]) {
                Collections.swap(adj[node], 0, i);
            }
        }
    }

    static void heavyLightDecomposition(int node, int depth) {
        HLDNum[node] = HLDCount++;
        HLDDepth[node] = depth;

        for (int child : adj[node]) {
            if (child == adj[node].get(0)) {
                HLDHead[child] = HLDHead[node];
                HLDParent[child] = HLDParent[node];
                heavyLightDecomposition(child, depth);
            } else {
                HLDHead[child] = child;
                HLDParent[child] = node;
                heavyLightDecomposition(child, depth + 1);
            }
            updateTree(1, 1, N, HLDNum[child], 1);
        }
    }

    static void updateTree(int node, int start, int end, int index, int value) {
        if (index < start || index > end) return;
        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;
        updateTree(node * 2, start, mid, index, value);
        updateTree(node * 2 + 1, mid + 1, end, index, value);
        tree[node] = tree[node * 2] & tree[node * 2 + 1];
    }

    static int queryTree(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return 1;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return queryTree(node * 2, start, mid, left, right) &
               queryTree(node * 2 + 1, mid + 1, end, left, right);
    }
}
