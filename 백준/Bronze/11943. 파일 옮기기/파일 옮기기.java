import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 첫 번째 바구니 입력 (A: 사과, B: 오렌지)
        int A = sc.nextInt();
        int B = sc.nextInt();

        // 두 번째 바구니 입력 (C: 사과, D: 오렌지)
        int C = sc.nextInt();
        int D = sc.nextInt();

        // 최소 이동 횟수 계산
        int minMoves = Math.min(A + D, B + C);

        // 결과 출력
        System.out.println(minMoves);

        sc.close();
    }
}
