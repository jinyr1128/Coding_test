import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 민국이 점수 입력
        int mingukTotal = 0;
        for (int i = 0; i < 4; i++) {
            mingukTotal += scanner.nextInt();
        }

        // 만세 점수 입력
        int manseTotal = 0;
        for (int i = 0; i < 4; i++) {
            manseTotal += scanner.nextInt();
        }

        // 더 큰 점수를 출력, 동점일 경우 민국이의 점수 출력
        System.out.println(Math.max(mingukTotal, manseTotal));

        scanner.close();
    }
}