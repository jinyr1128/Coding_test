import java.io.*;
import java.util.*;

public class Main {
    static class State {
        int position;
        int moves;

        public State(int position, int moves) {
            this.position = position;
            this.moves = moves;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] board = new int[101]; // 게임판
        for (int i = 1; i <= 100; i++) {
            board[i] = i; // 초기에는 자기 자신으로 설정
        }

        // 사다리 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x] = y;
        }

        // 뱀 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            board[u] = v;
        }

        System.out.println(bfs(board));
    }

    private static int bfs(int[] board) {
        boolean[] visited = new boolean[101]; // 방문 체크 배열
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(1, 0)); // 1번 칸에서 시작, 0회 이동

        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.position == 100) return current.moves; // 100번 칸 도착

            for (int i = 1; i <= 6; i++) { // 주사위 눈금 1-6
                int nextPos = current.position + i;
                if (nextPos > 100) continue; // 100을 넘으면 이동 불가

                nextPos = board[nextPos]; // 사다리나 뱀 이동 적용
                if (!visited[nextPos]) {
                    visited[nextPos] = true;
                    queue.add(new State(nextPos, current.moves + 1));
                }
            }
        }
        return -1; // 도달할 수 없는 경우
    }
}
