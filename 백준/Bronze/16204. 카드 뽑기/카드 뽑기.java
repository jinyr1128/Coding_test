import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 전체 카드 수
        int M = sc.nextInt(); // 앞면 O 수
        int K = sc.nextInt(); // 뒷면 O 수

        int frontO = M;
        int frontX = N - M;

        int backO = K;
        int backX = N - K;

        int sameO = Math.min(frontO, backO);
        int sameX = Math.min(frontX, backX);

        System.out.println(sameO + sameX);
    }
}
