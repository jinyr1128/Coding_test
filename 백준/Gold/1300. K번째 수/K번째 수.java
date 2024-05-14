import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.close();
        
        // 이진 탐색을 위한 초기값 설정
        long left = 1;
        long right = (long) N * N;
        long answer = 0;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            if (countLessEqual(mid, N) < k) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }
        
        System.out.println(answer);
    }

    // mid 이하의 숫자가 몇 개 있는지 세는 함수
    private static long countLessEqual(long mid, int N) {
        long count = 0;
        for (int i = 1; i <= N; i++) {
            count += Math.min(mid / i, N);
        }
        return count;
    }
}
