import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 두 통장의 잔고 입력받기
        long A = sc.nextLong();
        long B = sc.nextLong();

        // 치킨 한 마리의 가격 입력받기
        long C = sc.nextLong();

        // 치킨 두 마리의 가격
        long chickenCost = 2 * C;

        // 최종 남은 잔고 계산
        long result = (A + B >= chickenCost) ? (A + B - chickenCost) : (A + B);

        // 결과 출력
        System.out.println(result);

        sc.close();
    }
}
