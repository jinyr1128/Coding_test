import java.io.*;
import java.util.*;

public class Main {

    static class Query implements Comparable<Query> {
        int left, right, index;

        public Query(int left, int right, int index) {
            this.left = left;
            this.right = right;
            this.index = index;
        }

        @Override
        public int compareTo(Query o) {
            int currentBlock = this.left / blockSize;
            int otherBlock = o.left / blockSize;

            if (currentBlock != otherBlock) {
                return Integer.compare(currentBlock, otherBlock);
            }
            return Integer.compare(this.right, o.right);
        }
    }

    static int[] color, count, answers;
    static int blockSize, currentLeft, currentRight;
    static int maxN = 300001, maxC = 10001;
    static int[] currentCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        color = new int[n];
        count = new int[maxC];
        answers = new int[maxN];
        currentCount = new int[maxC];
        Arrays.fill(answers, -1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            color[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        Query[] queries = new Query[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            queries[i] = new Query(a, b, i);
        }

        // 블록 크기 계산 및 쿼리 정렬
        blockSize = (int) Math.sqrt(n);
        Arrays.sort(queries);

        // Mo's Algorithm 적용
        processQueries(n, m, queries);

        // 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < m; i++) {
            if (answers[i] != -1) {
                bw.write("yes " + answers[i] + "\n");
            } else {
                bw.write("no\n");
            }
        }
        bw.flush();
    }

    static void processQueries(int n, int m, Query[] queries) {
        currentLeft = 0;
        currentRight = -1;

        for (Query query : queries) {
            while (currentLeft > query.left) {
                currentLeft--;
                add(currentLeft);
            }
            while (currentRight < query.right) {
                currentRight++;
                add(currentRight);
            }
            while (currentLeft < query.left) {
                remove(currentLeft);
                currentLeft++;
            }
            while (currentRight > query.right) {
                remove(currentRight);
                currentRight--;
            }

            int majorityColor = findMajorityColor(query.left, query.right);
            if (majorityColor != -1) {
                answers[query.index] = majorityColor;
            }
        }
    }

    static void add(int index) {
        int col = color[index];
        count[col]++;
    }

    static void remove(int index) {
        int col = color[index];
        count[col]--;
    }

    static int findMajorityColor(int left, int right) {
        int len = right - left + 1;
        for (int i = 0; i < maxC; i++) {
            if (count[i] * 2 > len) {
                return i;
            }
        }
        return -1;
    }
}
