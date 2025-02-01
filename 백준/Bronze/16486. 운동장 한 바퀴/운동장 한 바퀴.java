import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scanner를 사용하여 입력 받기
        Scanner sc = new Scanner(System.in);

        // d1 (직사각형의 가로 길이)
        int d1 = sc.nextInt();

        // d2 (반원의 반지름 길이)
        int d2 = sc.nextInt();

        // π 값 정의
        final double PI = 3.141592;

        // 운동장의 한 바퀴 둘레 계산
        double perimeter = (2 * d1) + (2 * PI * d2);

        // 결과 출력 (소수점 6자리까지 출력)
        System.out.printf("%.6f%n", perimeter);

        // Scanner 닫기
        sc.close();
    }
}
