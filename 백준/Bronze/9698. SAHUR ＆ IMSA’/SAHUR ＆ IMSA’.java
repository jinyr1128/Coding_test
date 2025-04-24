import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();  // 테스트 케이스 개수

        for (int t = 1; t <= T; t++) {
            int H = sc.nextInt();  // 시
            int M = sc.nextInt();  // 분

            int totalMinutes = H * 60 + M;
            int newMinutes = totalMinutes - 45;

            if (newMinutes < 0) {
                newMinutes += 24 * 60;  // 하루 전으로 이동
            }

            int newH = newMinutes / 60;
            int newM = newMinutes % 60;

            System.out.printf("Case #%d: %d %d%n", t, newH, newM);
        }

        sc.close();
    }
}
