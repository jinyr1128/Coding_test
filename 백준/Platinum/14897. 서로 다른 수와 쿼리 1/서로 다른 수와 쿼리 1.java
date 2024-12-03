import java.io.*;
import java.util.*;

public class Main {

    static class Query implements Comparable<Query> {
        int idx, left, right, block;

        Query(int idx, int left, int right, int block) {
            this.idx = idx;
            this.left = left;
            this.right = right;
            this.block = block;
        }

        @Override
        public int compareTo(Query other) {
            if (this.block != other.block) {
                return Integer.compare(this.block, other.block);
            }
            return Integer.compare(this.right, other.right);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int sqrtN = (int) Math.sqrt(n);
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // Coordinate compression
        int[] compressed = Arrays.copyOf(arr, n);
        Arrays.sort(compressed);
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        int idx = 0;
        for (int val : compressed) {
            if (!valueToIndex.containsKey(val)) {
                valueToIndex.put(val, idx++);
            }
        }
        for (int i = 0; i < n; i++) {
            arr[i] = valueToIndex.get(arr[i]);
        }

        int q = Integer.parseInt(br.readLine());
        Query[] queries = new Query[q];
        int[] results = new int[q];

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            queries[i] = new Query(i, l, r, l / sqrtN);
        }

        Arrays.sort(queries);

        int[] freq = new int[n];
        int distinctCount = 0;

        int currentLeft = 0, currentRight = -1;

        for (Query query : queries) {
            while (currentRight < query.right) {
                currentRight++;
                distinctCount += add(arr[currentRight], freq);
            }
            while (currentRight > query.right) {
                distinctCount -= remove(arr[currentRight], freq);
                currentRight--;
            }
            while (currentLeft < query.left) {
                distinctCount -= remove(arr[currentLeft], freq);
                currentLeft++;
            }
            while (currentLeft > query.left) {
                currentLeft--;
                distinctCount += add(arr[currentLeft], freq);
            }
            results[query.idx] = distinctCount;
        }

        for (int result : results) {
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int add(int value, int[] freq) {
        if (freq[value] == 0) {
            freq[value]++;
            return 1;
        }
        freq[value]++;
        return 0;
    }

    private static int remove(int value, int[] freq) {
        if (freq[value] == 1) {
            freq[value]--;
            return 1;
        }
        freq[value]--;
        return 0;
    }
}
