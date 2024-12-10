import java.io.*;
import java.util.*;

public class Main {

    static int[] tree;
    static int size;

    // 세그먼트 트리 업데이트
    static void update(int idx, int value) {
        idx += size;
        tree[idx] = value;
        while (idx > 1) {
            idx /= 2;
            tree[idx] = Math.min(tree[2 * idx], tree[2 * idx + 1]);
        }
    }

    // 세그먼트 트리 쿼리
    static int query(int left, int right) {
        left += size;
        right += size;
        int minValue = Integer.MAX_VALUE;

        while (left <= right) {
            if (left % 2 == 1) minValue = Math.min(minValue, tree[left++]);
            if (right % 2 == 0) minValue = Math.min(minValue, tree[right--]);
            left /= 2;
            right /= 2;
        }

        return minValue;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] ranks = new int[N][3];
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int student = Integer.parseInt(st.nextToken());
                ranks[student - 1][i] = j + 1;
            }
        }

        // 학생들을 첫 번째 시험 순서대로 정렬
        Arrays.sort(ranks, Comparator.comparingInt(a -> a[0]));

        // 세그먼트 트리 초기화
        size = 1;
        while (size < N) size *= 2;
        tree = new int[2 * size];
        Arrays.fill(tree, Integer.MAX_VALUE);

        int result = N;
        for (int[] rank : ranks) {
            int rank1 = rank[0];
            int rank2 = rank[1];
            int rank3 = rank[2];

            if (query(0, rank2 - 1) < rank3) {
                result--;
            }
            update(rank2, rank3);
        }

        System.out.println(result);
    }
}
