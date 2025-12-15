import java.util.Scanner;

public class Main {
    static int[][] board = new int[5][5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 빙고판 입력
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        // 2. 사회자가 부르는 수 입력 및 시뮬레이션
        int count = 0; // 사회자가 부른 수의 개수

        // 사회자가 부르는 25개의 숫자를 순서대로 처리
        for (int k = 0; k < 25; k++) {
            int num = sc.nextInt();
            count++;

            // 해당 숫자를 빙고판에서 찾아 지움 (0으로 변경)
            markNumber(num);

            // 빙고 줄 개수 체크
            if (checkBingo() >= 3) {
                System.out.println(count);
                break;
            }
        }
        
        sc.close();
    }

    // 숫자를 찾아서 0으로 바꾸는 함수
    public static void markNumber(int num) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == num) {
                    board[i][j] = 0; // 지워진 숫자는 0으로 표시
                    return;
                }
            }
        }
    }

    // 현재 상태에서 완성된 줄(빙고)의 개수를 세는 함수
    public static int checkBingo() {
        int bingoCount = 0;

        // 가로줄 검사
        for (int i = 0; i < 5; i++) {
            int checked = 0;
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == 0) checked++;
            }
            if (checked == 5) bingoCount++;
        }

        // 세로줄 검사
        for (int j = 0; j < 5; j++) {
            int checked = 0;
            for (int i = 0; i < 5; i++) {
                if (board[i][j] == 0) checked++;
            }
            if (checked == 5) bingoCount++;
        }

        // 대각선 (왼쪽 위 -> 오른쪽 아래) 검사
        int diag1 = 0;
        for (int i = 0; i < 5; i++) {
            if (board[i][i] == 0) diag1++;
        }
        if (diag1 == 5) bingoCount++;

        // 대각선 (오른쪽 위 -> 왼쪽 아래) 검사
        int diag2 = 0;
        for (int i = 0; i < 5; i++) {
            if (board[i][4 - i] == 0) diag2++;
        }
        if (diag2 == 5) bingoCount++;

        return bingoCount;
    }
}