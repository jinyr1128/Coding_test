import java.io.*;
import java.util.*;

class Main {

    static class Shark {
        int speed, direction, size;

        Shark(int speed, int direction, int size) {
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }

    static int R, C, M;
    static int answerSumOfSize = 0;
    static Shark[][] sharks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 격자판의 크기 R, C, 상어의 수 M
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sharks = new Shark[R][C];

        // M 개의 줄에 상어의 정보
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sharks[r - 1][c - 1] = new Shark(s, d, z);
        }

        solution();
        System.out.println(answerSumOfSize);
    }

    // 낚시왕이 오른쪽으로 한칸 이동하는건 반복문의 index 로 표현
    // 현재 상어의 위치 중 제일 가까운 상어를 잡고 상어 이동 반복
    private static void solution() {
        for (int col = 0; col < C; col++) {
            fishing(col);
            moveAllSharks();
        }
    }

    // 현재 위치에서 가장 가까이에 있는 상어를 잡는다.
    private static void fishing(int col) {
        for (int row = 0; row < R; row++) {
            if (sharks[row][col] != null) {
                answerSumOfSize += sharks[row][col].size;
                sharks[row][col] = null;
                return;
            }
        }
    }

    private static void moveAllSharks() {
        Shark[][] nextSharks = new Shark[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (sharks[i][j] != null) {
                    moveShark(nextSharks, i, j);
                }
            }
        }

        sharks = nextSharks;
    }

    private static void moveShark(Shark[][] nextSharks, int i, int j) {
        Shark shark = sharks[i][j];

        int row = i;
        int col = j;
        int moveDistance = shark.speed;

        // 이동 거리를 제한하여 경계를 넘을 때 방향 전환을 더 효율적으로 처리
        if (shark.direction == 1 || shark.direction == 2) {
            moveDistance %= (R - 1) * 2;
        } else {
            moveDistance %= (C - 1) * 2;
        }

        while (moveDistance > 0) {
            if (shark.direction == 1) {
                row--;
                if (row < 0) {
                    shark.direction = 2;
                    row = 1;
                }
            } else if (shark.direction == 2) {
                row++;
                if (row >= R) {
                    shark.direction = 1;
                    row = R - 2;
                }
            } else if (shark.direction == 3) {
                col++;
                if (col >= C) {
                    shark.direction = 4;
                    col = C - 2;
                }
            } else if (shark.direction == 4) {
                col--;
                if (col < 0) {
                    shark.direction = 3;
                    col = 1;
                }
            }
            moveDistance--;
        }

        // 만약 이미 상어가 있으면 크기를 비교해서 큰 상어를 남긴다
        if (nextSharks[row][col] == null || nextSharks[row][col].size < shark.size) {
            nextSharks[row][col] = shark;
        }
    }
}
