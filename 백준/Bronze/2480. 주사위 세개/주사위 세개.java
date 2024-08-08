import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 세 개의 주사위 눈을 입력받음
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        int prize = calculatePrize(a, b, c);

        // 결과 출력
        System.out.println(prize);

        scanner.close();
    }

    // 상금 계산 메서드
    private static int calculatePrize(int a, int b, int c) {
        if (a == b && b == c) {
            // 세 눈이 모두 같은 경우
            return 10000 + a * 1000;
        } else if (a == b || a == c) {
            // 두 눈이 같은 경우 (a와 b 또는 a와 c)
            return 1000 + a * 100;
        } else if (b == c) {
            // 두 눈이 같은 경우 (b와 c)
            return 1000 + b * 100;
        } else {
            // 모두 다른 경우
            return Math.max(a, Math.max(b, c)) * 100;
        }
    }
}

