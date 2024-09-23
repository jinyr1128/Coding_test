import java.util.*;

public class Main {
    static char[][] arr = new char[7][7];
    static int[][] conn = new int[7][7];
    static int cnt = 13; // 최댓값보다 높은 초기값
    static int[] dy = {-1, 0, 1, 0}; // 상하좌우 이동
    static int[] dx = {0, -1, 0, 1};
    static int mainBit = 1 << 26; // 비트마스크를 위한 초기값
    static Map<Integer, Integer> check = new HashMap<>(); // 방문 체크
    static List<int[]> piece = new ArrayList<>(); // 조각의 위치 저장

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력 받기 및 초기 설정
        for (int i = 1; i <= 5; i++) {
            String str = sc.next();
            for (int j = 1; j <= 5; j++) {
                arr[i][j] = str.charAt(j - 1);
                if (arr[i][j] == '*') {
                    piece.add(new int[]{i, j});
                    mainBit |= (1 << ((5 * (i - 1)) + j));
                }
            }
        }

        // 초기 상태 저장
        check.put(mainBit, 0);
        getPiece(0, mainBit);
        System.out.println(cnt);
        sc.close();
    }

    // 연결된 조각의 수를 계산하는 함수
    static int getConn(int y, int x) {
        conn[y][x] = 1;
        int num = 1;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (arr[ny][nx] != '*' || ny > 5 || nx > 5 || ny < 1 || nx < 1 || conn[ny][nx] != 0) {
                continue;
            }
            num += getConn(ny, nx);
        }
        return num;
    }

    // 최소 이동 횟수를 계산하는 재귀 함수
    static void getPiece(int _cnt, int bit) {
        // 연결 확인을 위한 초기화
        for (int i = 1; i <= 5; i++) {
            Arrays.fill(conn[i], 0);
        }
        
        // 모든 조각이 연결되어 있는지 확인
        if (getConn(piece.get(0)[0], piece.get(0)[1]) == piece.size()) {
            cnt = _cnt;
            return;
        }

        // 현재 이동 횟수가 최소값보다 크거나 같으면 종료
        if (_cnt >= cnt) {
            return;
        }

        // 각 조각을 이동시켜서 다음 상태를 계산
        for (int i = 0; i < piece.size(); i++) {
            int y = piece.get(i)[0];
            int x = piece.get(i)[1];
            for (int j = 0; j < 4; j++) {
                int ny = y + dy[j];
                int nx = x + dx[j];
                if (ny < 1 || nx < 1 || ny > 5 || nx > 5 || arr[ny][nx] == '*') {
                    continue;
                }

                int pos2d = bit | (1 << ((5 * (ny - 1)) + nx));
                pos2d = pos2d & ~(1 << (5 * (y - 1) + x));

                if (check.containsKey(pos2d) && check.get(pos2d) <= _cnt + 1) {
                    continue;
                }

                check.put(pos2d, _cnt + 1);
                piece.set(i, new int[]{ny, nx});
                arr[ny][nx] = '*';
                arr[y][x] = '.';

                getPiece(_cnt + 1, pos2d);

                // 원래 상태로 복구
                arr[ny][nx] = '.';
                arr[y][x] = '*';
                piece.set(i, new int[]{y, x});
            }
        }
    }
}
