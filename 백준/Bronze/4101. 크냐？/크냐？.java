import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            // 입력의 마지막 줄 (0 0) 처리
            if (a == 0 && b == 0) {
                break;
            }

            // 첫 번째 수가 두 번째 수보다 큰 경우 Yes 출력, 그렇지 않으면 No 출력
            if (a > b) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

        scanner.close();
    }
}
