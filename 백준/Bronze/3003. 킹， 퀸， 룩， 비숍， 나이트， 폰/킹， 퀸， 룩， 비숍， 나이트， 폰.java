import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 각 피스의 정답 개수
        int[] correctPieces = {1, 1, 2, 2, 2, 8};

        // 입력받은 현재 피스의 개수
        int[] currentPieces = new int[6];

        // 입력 받기
        for (int i = 0; i < 6; i++) {
            currentPieces[i] = scanner.nextInt();
        }

        // 결과 계산 및 출력
        for (int i = 0; i < 6; i++) {
            System.out.print(correctPieces[i] - currentPieces[i] + " ");
        }

        scanner.close();
    }
}
