import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 행(R)과 열(C) 입력
        int R = sc.nextInt();
        int C = sc.nextInt();

        // 2. 지도 정보 입력
        char[][] map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = sc.next();
            map[i] = line.toCharArray();
        }

        int foundCount = 0;
        int foundRow = -1;
        int foundCol = -1;

        // 8방향 델타 배열 (좌상, 상, 우상, 좌, 우, 좌하, 하, 우하)
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        // 3. 탐색 (가장자리는 8칸에 둘러싸일 수 없으므로 제외하고 탐색)
        for (int r = 1; r < R - 1; r++) {
            for (int c = 1; c < C - 1; c++) {
                
                // 현재 칸이 '0'인 경우에만 주변 확인
                if (map[r][c] == '0') {
                    boolean isTreasure = true;

                    // 주변 8칸 확인
                    for (int k = 0; k < 8; k++) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];
                        
                        // 하나라도 'O'가 아니면 보물이 아님
                        if (map[nr][nc] != 'O') {
                            isTreasure = false;
                            break;
                        }
                    }

                    // 4. 보물 위치 발견 시 처리
                    if (isTreasure) {
                        foundCount++;
                        foundRow = r + 1; // 1-based index로 저장
                        foundCol = c + 1;
                    }
                }
            }
        }

        // 5. 결과 출력
        if (foundCount == 0) {
            System.out.println("Oh no!");
        } else if (foundCount == 1) {
            System.out.println(foundRow + " " + foundCol);
        } else {
            System.out.println("Oh no! " + foundCount + " locations");
        }
        
        sc.close();
    }
}