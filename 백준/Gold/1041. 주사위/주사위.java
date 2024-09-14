import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();
        long answer = 0;

        int[] arr = new int[6];
        int max_num = 0;

        // 주사위 각 면의 값을 입력받고, 총합과 가장 큰 값을 계산
        for (int i = 0; i < 6; i++) {
            arr[i] = sc.nextInt();
            answer += arr[i];
            max_num = Math.max(max_num, arr[i]);
        }

        // n이 1인 경우: 가장 큰 면의 값을 제외한 모든 값을 더한 후 출력
        if (n == 1) {
            System.out.println(answer - max_num);
        } else {
            answer = 0;
            // 마주보는 면 중 최소값으로 대체
            arr[0] = Math.min(arr[0], arr[5]);
            arr[1] = Math.min(arr[1], arr[4]);
            arr[2] = Math.min(arr[2], arr[3]);

            // 3개의 최소값을 정렬
            int[] sorted = {arr[0], arr[1], arr[2]};
            Arrays.sort(sorted);

            int sum1 = sorted[0];
            int sum2 = sum1 + sorted[1];
            int sum3 = sum2 + sorted[2];

            // 최종 계산
            answer += sum3 * 4;
            answer += sum2 * (4 * (n - 2) + 4 * (n - 1));
            answer += sum1 * (4 * (n - 1) * (n - 2) + (n - 2) * (n - 2));

            System.out.println(answer);
        }

        sc.close();
    }
}
