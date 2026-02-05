import java.util.*;
import java.io.*;

public class Main {
    // 8방향에 대한 좌표 변화량 (행, 열)
    // 0: South (하)
    // 1: South-West (좌하)
    // 2: West (좌)
    // 3: North-West (좌상)
    // 4: North (상)
    // 5: North-East (우상)
    // 6: East (우)
    // 7: South-East (우하)
    static final int[][] DELTAS = {
        {1, 0},   // 0
        {1, -1},  // 1
        {0, -1},  // 2
        {-1, -1}, // 3
        {-1, 0},  // 4
        {-1, 1},  // 5
        {0, 1},   // 6
        {1, 1}    // 7
    };

    // 알파벳 A-Z에 해당하는 팔의 방향 쌍 (오름차순 정렬됨)
    // 표준 세마포어 신호를 기반으로 작성
    static final int[][] ALPHA_MAP = {
        {0,1}, // A
        {0,2}, // B
        {0,3}, // C
        {0,4}, // D
        {0,5}, // E
        {0,6}, // F
        {0,7}, // G
        {1,2}, // H
        {1,3}, // I
        {4,6}, // J (일반적인 순서와 다름, North + East)
        {1,4}, // K
        {1,5}, // L
        {1,6}, // M
        {1,7}, // N
        {2,3}, // O
        {2,4}, // P
        {2,5}, // Q
        {2,6}, // R
        {2,7}, // S
        {3,4}, // T
        {3,5}, // U
        {4,7}, // V
        {5,6}, // W
        {5,7}, // X
        {3,6}, // Y
        {6,7}  // Z
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String line = br.readLine();
        if (line == null) return;
        
        StringTokenizer st = new StringTokenizer(line);
        int N = Integer.parseInt(st.nextToken()); // 단어 길이
        int C = Integer.parseInt(st.nextToken()); // 카이사르 암호 키

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            // 9x9 그리드 입력 받기
            char[][] grid = new char[9][9];
            for (int r = 0; r < 9; r++) {
                grid[r] = br.readLine().toCharArray();
            }

            // 팔의 방향 찾기 (중심 (4,4) 주변 탐색)
            List<Integer> limbs = new ArrayList<>();
            for (int d = 0; d < 8; d++) {
                int r = 4 + DELTAS[d][0];
                int c = 4 + DELTAS[d][1];
                if (grid[r][c] == '#') {
                    limbs.add(d);
                }
            }
            Collections.sort(limbs); // 비교를 위해 정렬

            // 해당하는 문자 찾기
            int charIdx = -1;
            int l1 = limbs.get(0);
            int l2 = limbs.get(1);

            for (int k = 0; k < 26; k++) {
                if (ALPHA_MAP[k][0] == l1 && ALPHA_MAP[k][1] == l2) {
                    charIdx = k;
                    break;
                }
            }

            // 카이사르 암호 적용
            int encodedIdx = (charIdx + C) % 26;

            // 새로운 그리드 생성
            char[][] outGrid = new char[9][9];
            for(int r=0; r<9; r++) Arrays.fill(outGrid[r], '.');
            outGrid[4][4] = '*'; // 중심

            // 새로운 팔 그리기
            int d1 = ALPHA_MAP[encodedIdx][0];
            int d2 = ALPHA_MAP[encodedIdx][1];

            drawLimb(outGrid, d1);
            drawLimb(outGrid, d2);

            // 출력 버퍼에 추가
            for (int r = 0; r < 9; r++) {
                sb.append(outGrid[r]).append('\n');
            }
        }
        System.out.print(sb);
    }

    // 특정 방향으로 팔을 그리는 함수
    static void drawLimb(char[][] grid, int d) {
        for (int k = 1; k <= 3; k++) {
            int r = 4 + DELTAS[d][0] * k;
            int c = 4 + DELTAS[d][1] * k;
            grid[r][c] = '#';
        }
    }
}