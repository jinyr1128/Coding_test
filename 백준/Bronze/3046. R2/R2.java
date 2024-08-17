import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받기: R1과 S
        int R1 = scanner.nextInt();
        int S = scanner.nextInt();

        // R2 계산: R2 = 2 * S - R1
        int R2 = 2 * S - R1;

        // R2 출력
        System.out.println(R2);
    }
}
