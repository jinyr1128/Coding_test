import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] liquids = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            liquids[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = n - 1;
        int minSum = Integer.MAX_VALUE;
        int answer1 = 0, answer2 = 0;

        while (left < right) {
            int sum = liquids[left] + liquids[right];

            // 0에 가까운 합을 찾기
            if (Math.abs(sum) < minSum) {
                minSum = Math.abs(sum);
                answer1 = liquids[left];
                answer2 = liquids[right];
            }

            if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(answer1 + " " + answer2);
    }
}
