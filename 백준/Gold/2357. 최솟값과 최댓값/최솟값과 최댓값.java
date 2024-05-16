import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int[] array;
    static int[] minSegmentTree;
    static int[] maxSegmentTree;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }
        
        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        int treeSize = (1 << (h + 1));
        minSegmentTree = new int[treeSize];
        maxSegmentTree = new int[treeSize];
        
        buildMinSegmentTree(1, 0, n - 1);
        buildMaxSegmentTree(1, 0, n - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int minVal = queryMin(1, 0, n - 1, a, b);
            int maxVal = queryMax(1, 0, n - 1, a, b);
            sb.append(minVal).append(" ").append(maxVal).append("\n");
        }
        
        System.out.print(sb.toString());
    }

    static void buildMinSegmentTree(int node, int start, int end) {
        if (start == end) {
            minSegmentTree[node] = array[start];
        } else {
            int mid = (start + end) / 2;
            buildMinSegmentTree(node * 2, start, mid);
            buildMinSegmentTree(node * 2 + 1, mid + 1, end);
            minSegmentTree[node] = Math.min(minSegmentTree[node * 2], minSegmentTree[node * 2 + 1]);
        }
    }

    static void buildMaxSegmentTree(int node, int start, int end) {
        if (start == end) {
            maxSegmentTree[node] = array[start];
        } else {
            int mid = (start + end) / 2;
            buildMaxSegmentTree(node * 2, start, mid);
            buildMaxSegmentTree(node * 2 + 1, mid + 1, end);
            maxSegmentTree[node] = Math.max(maxSegmentTree[node * 2], maxSegmentTree[node * 2 + 1]);
        }
    }

    static int queryMin(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return Integer.MAX_VALUE;
        }
        if (left <= start && end <= right) {
            return minSegmentTree[node];
        }
        int mid = (start + end) / 2;
        int leftMin = queryMin(node * 2, start, mid, left, right);
        int rightMin = queryMin(node * 2 + 1, mid + 1, end, left, right);
        return Math.min(leftMin, rightMin);
    }

    static int queryMax(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return Integer.MIN_VALUE;
        }
        if (left <= start && end <= right) {
            return maxSegmentTree[node];
        }
        int mid = (start + end) / 2;
        int leftMax = queryMax(node * 2, start, mid, left, right);
        int rightMax = queryMax(node * 2 + 1, mid + 1, end, left, right);
        return Math.max(leftMax, rightMax);
    }
}
