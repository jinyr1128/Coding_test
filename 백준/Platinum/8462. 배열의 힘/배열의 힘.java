import java.io.*;
import java.util.*;

public class Main {

    static class Query implements Comparable<Query> {
        int left, right, index, block;

        Query(int left, int right, int index, int block) {
            this.left = left;
            this.right = right;
            this.index = index;
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1]; // 1-based indexing
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sqrtN = (int) Math.sqrt(n);
        Query[] queries = new Query[t];

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            queries[i] = new Query(l, r, i, l / sqrtN);
        }

        Arrays.sort(queries);

        long[] results = new long[t];
        long currentValue = 0;
        int[] count = new int[1000001];
        int currentLeft = 1, currentRight = 0;

        for (Query q : queries) {
            while (currentRight < q.right) {
                currentRight++;
                currentValue += add(arr[currentRight], count);
            }
            while (currentRight > q.right) {
                currentValue -= remove(arr[currentRight], count);
                currentRight--;
            }
            while (currentLeft < q.left) {
                currentValue -= remove(arr[currentLeft], count);
                currentLeft++;
            }
            while (currentLeft > q.left) {
                currentLeft--;
                currentValue += add(arr[currentLeft], count);
            }
            results[q.index] = currentValue;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (long res : results) {
            bw.write(res + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static long add(int value, int[] count) {
        count[value]++;
        return (long) count[value] * count[value] * value 
             - (long) (count[value] - 1) * (count[value] - 1) * value;
    }

    private static long remove(int value, int[] count) {
        long currentContribution = (long) count[value] * count[value] * value;
        count[value]--;
        return currentContribution 
             - (long) count[value] * count[value] * value;
    }
}
