import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 테스트 케이스 개수 입력
        int T = Integer.parseInt(sc.nextLine());

        for (int caseNum = 1; caseNum <= T; caseNum++) {
            // 1. 틱택토 보드 입력
            char[][] board = new char[3][3];
            board[0] = sc.nextLine().toCharArray();
            board[1] = sc.nextLine().toCharArray();
            board[2] = sc.nextLine().toCharArray();

            // 2. 남규의 말 입력
            char playerMark = sc.nextLine().charAt(0);

            int winR = -1; // 승리하는 위치의 행
            int winC = -1; // 승리하는 위치의 열
            boolean found = false;

            // 3. 가로 3줄 확인
            for (int r = 0; r < 3; r++) {
                int playerCount = 0;
                int emptyCount = 0;
                int emptyC = -1; // 빈 칸의 열 위치
                
                for (int c = 0; c < 3; c++) {
                    if (board[r][c] == playerMark) {
                        playerCount++;
                    } else if (board[r][c] == '-') {
                        emptyCount++;
                        emptyC = c;
                    }
                }
                
                if (playerCount == 2 && emptyCount == 1) {
                    winR = r;
                    winC = emptyC;
                    found = true;
                    break;
                }
            }

            // 4. 세로 3줄 확인 (가로에서 못 찾았다면)
            if (!found) {
                for (int c = 0; c < 3; c++) {
                    int playerCount = 0;
                    int emptyCount = 0;
                    int emptyR = -1; // 빈 칸의 행 위치
                    
                    for (int r = 0; r < 3; r++) {
                        if (board[r][c] == playerMark) {
                            playerCount++;
                        } else if (board[r][c] == '-') {
                            emptyCount++;
                            emptyR = r;
                        }
                    }
                    
                    if (playerCount == 2 && emptyCount == 1) {
                        winR = emptyR;
                        winC = c;
                        found = true;
                        break;
                    }
                }
            }

            // 5. 대각선 (좌상 -> 우하) 확인
            if (!found) {
                int playerCount = 0;
                int emptyCount = 0;
                int emptyIdx = -1; // (0,0), (1,1), (2,2) 중 빈 칸의 인덱스
                
                for (int i = 0; i < 3; i++) {
                    if (board[i][i] == playerMark) {
                        playerCount++;
                    } else if (board[i][i] == '-') {
                        emptyCount++;
                        emptyIdx = i;
                    }
                }
                
                if (playerCount == 2 && emptyCount == 1) {
                    winR = emptyIdx;
                    winC = emptyIdx;
                    found = true;
                }
            }

            // 6. 대각선 (우상 -> 좌하) 확인
            if (!found) {
                int playerCount = 0;
                int emptyCount = 0;
                int emptyR = -1;
                int emptyC = -1;
                
                for (int i = 0; i < 3; i++) {
                    int r = i;
                    int c = 2 - i; // (0,2), (1,1), (2,0)
                    if (board[r][c] == playerMark) {
                        playerCount++;
                    } else if (board[r][c] == '-') {
                        emptyCount++;
                        emptyR = r;
                        emptyC = c;
                    }
                }
                
                if (playerCount == 2 && emptyCount == 1) {
                    winR = emptyR;
                    winC = emptyC;
                    // 'found = true;' (어차피 마지막 확인임)
                }
            }
            
            // 7. 승리 위치에 말 놓기
            board[winR][winC] = playerMark;

            // 8. 결과 출력
            System.out.println("Case " + caseNum + ":");
            for (int r = 0; r < 3; r++) {
                System.out.println(new String(board[r]));
            }
        }
        sc.close();
    }
}