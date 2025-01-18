import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력값 A, B 받기
        int A = sc.nextInt();
        int B = sc.nextInt();

        // 톰이 가진 치즈 무게 계산: 1 - (A/B) = (B - A) / B
        int P = B - A;  // 남은 치즈의 분자
        int Q = B;      // 남은 치즈의 분모

        // 결과 출력
        System.out.println(P + " " + Q);

        sc.close();
    }
}
