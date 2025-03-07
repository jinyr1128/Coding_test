import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 성우의 현재 위치와 민건이 집까지의 거리 L 입력
        int L = sc.nextInt();

        // 최소 시간 계산 (올림 연산)
        int t = (L + 4) / 5;

        // 결과 출력
        System.out.println(t);

        sc.close();
    }
}
