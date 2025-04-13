import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();  // 각 치킨 종류 수량
        int A = scanner.nextInt();  // 후라이드 선호자
        int B = scanner.nextInt();  // 간장 선호자
        int C = scanner.nextInt();  // 양념 선호자

        int maxSatisfied = Math.min(N, A) + Math.min(N, B) + Math.min(N, C);
        System.out.println(maxSatisfied);
    }
}
