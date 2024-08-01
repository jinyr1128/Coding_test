import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 성냥의 개수 N과 박스의 가로 W, 세로 H를 입력받습니다.
        int N = scanner.nextInt();
        int W = scanner.nextInt();
        int H = scanner.nextInt();

        // 박스의 대각선 길이를 계산합니다.
        double maxLength = Math.sqrt(W * W + H * H);

        // 각 성냥에 대해 박스에 들어갈 수 있는지 확인합니다.
        for (int i = 0; i < N; i++) {
            int matchLength = scanner.nextInt();

            if (matchLength <= maxLength) {
                System.out.println("DA"); // 박스에 들어갈 수 있는 경우
            } else {
                System.out.println("NE"); // 박스에 들어갈 수 없는 경우
            }
        }

        scanner.close();
    }
}
