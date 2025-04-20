import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt(); // 테스트 케이스 수

        while (T-- > 0) {
            int N = sc.nextInt();
            int X = sc.nextInt();
            int Y = sc.nextInt();

            int[] colors = new int[N];
            for (int i = 0; i < N; i++) {
                colors[i] = sc.nextInt();
            }

            boolean easyWrong = colors[0] == X;
            boolean hardWrong = colors[N - 1] == Y;

            if (easyWrong && hardWrong) {
                System.out.println("BOTH");
            } else if (easyWrong) {
                System.out.println("EASY");
            } else if (hardWrong) {
                System.out.println("HARD");
            } else {
                System.out.println("OKAY");
            }
        }

        sc.close();
    }
}
