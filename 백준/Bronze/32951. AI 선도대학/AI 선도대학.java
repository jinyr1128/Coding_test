import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // 입력 연도

        int result = N - 2024; // 기준 연도 2024로부터 몇 년 지났는지 계산
        System.out.println(result);
    }
}
