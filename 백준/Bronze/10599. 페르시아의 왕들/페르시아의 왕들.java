import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int a = sc.nextInt(); // 출생 최소 연도
            int b = sc.nextInt(); // 출생 최대 연도
            int c = sc.nextInt(); // 사망 최소 연도
            int d = sc.nextInt(); // 사망 최대 연도

            // 종료 조건
            if (a == 0 && b == 0 && c == 0 && d == 0) {
                break;
            }

            // 최소 나이와 최대 나이 계산
            int minAge = c - b;
            int maxAge = d - a;

            // 결과 출력
            System.out.println(minAge + " " + maxAge);
        }

        sc.close();
    }
}
