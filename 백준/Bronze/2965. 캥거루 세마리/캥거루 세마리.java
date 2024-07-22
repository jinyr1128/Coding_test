import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 세 캥거루의 초기 위치 입력
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = scanner.nextInt();

        // 캥거루가 움직일 수 있는 최대 횟수 계산
        int maxMoves = Math.max(B - A - 1, C - B - 1);

        // 결과 출력
        System.out.println(maxMoves);
    }
}
