import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static long[] liquids;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        liquids = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            liquids[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(liquids);  // 배열 정렬

        long closestSum = Long.MAX_VALUE;
        long[] result = new long[3];

        for (int i = 0; i < N - 2; i++) {
            int left = i + 1, right = N - 1;
            while (left < right) {
                long sum = liquids[i] + liquids[left] + liquids[right];
                if (Math.abs(sum) < Math.abs(closestSum)) {
                    closestSum = sum;  // 최소값 업데이트
                    result[0] = liquids[i];
                    result[1] = liquids[left];
                    result[2] = liquids[right];
                }

                if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        Arrays.sort(result); // 결과를 오름차순으로 출력
        for (long value : result) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
