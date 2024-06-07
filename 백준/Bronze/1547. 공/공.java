import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력: 컵 위치를 바꾼 횟수 M
        int M = scanner.nextInt();

        // 공이 있는 초기 위치 (처음엔 1번 컵)
        int ballPosition = 1;

        // M번 컵 위치 바꾸기
        for (int i = 0; i < M; i++) {
            int X = scanner.nextInt();
            int Y = scanner.nextInt();

            // X와 Y가 공이 있는 위치와 일치하면 공의 위치를 변경
            if (ballPosition == X) {
                ballPosition = Y;
            } else if (ballPosition == Y) {
                ballPosition = X;
            }
        }

        // 결과 출력: 공이 있는 컵의 번호
        System.out.println(ballPosition);
        
        scanner.close();
    }
}
