import java.util.Scanner;

public class Main {
    // 자릿수 합을 구하는 메서드
    private static int sumOfDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;  // 맨 마지막 자릿수 더하기
            num /= 10;        // 다음 자릿수로 이동
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int count = 0;

        for (int i = 1; i <= N; i++) {
            int digitSum = sumOfDigits(i);
            if (i % digitSum == 0) {
                count++;
            }
        }

        System.out.println(count);
    }
}
