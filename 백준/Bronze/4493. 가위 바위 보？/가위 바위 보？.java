import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // 테스트 케이스의 수
        scanner.nextLine(); // 개행 문자 처리

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt(); // 가위 바위 보를 한 횟수
            scanner.nextLine(); // 개행 문자 처리

            int player1Wins = 0;
            int player2Wins = 0;

            for (int j = 0; j < n; j++) {
                String[] moves = scanner.nextLine().split(" ");
                char player1 = moves[0].charAt(0);
                char player2 = moves[1].charAt(0);

                if (player1 == player2) {
                    // TIE 상황이므로 아무것도 하지 않음
                    continue;
                }

                // Player 1의 승리 조건
                if ((player1 == 'R' && player2 == 'S') ||
                    (player1 == 'S' && player2 == 'P') ||
                    (player1 == 'P' && player2 == 'R')) {
                    player1Wins++;
                } else {
                    player2Wins++;
                }
            }

            if (player1Wins > player2Wins) {
                System.out.println("Player 1");
            } else if (player1Wins < player2Wins) {
                System.out.println("Player 2");
            } else {
                System.out.println("TIE");
            }
        }

        scanner.close();
    }
}
