import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();

        // 실수 계산 후 정수로 버림
        int result1 = (int)(a / b * c);
        int result2 = (int)(a * b / c);

        System.out.println(Math.max(result1, result2));
    }
}
