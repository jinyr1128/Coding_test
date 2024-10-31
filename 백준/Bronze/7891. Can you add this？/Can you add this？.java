import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 첫 줄에서 테스트 케이스 수를 입력받음
        int t = scanner.nextInt();

        // 각 테스트 케이스에 대해 두 정수를 입력받고 합을 출력
        for (int i = 0; i < t; i++) {
            long x = scanner.nextLong();
            long y = scanner.nextLong();
            System.out.println(x + y);
        }

        scanner.close();
    }
}
