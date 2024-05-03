import java.util.*;

public class Main {
    static final int MAX = 10000;

    static class State {
        int number;
        String path;

        public State(int number, String path) {
            this.number = number;
            this.path = path;
        }
    }

    public static String bfs(int start, int end) {
        Queue<State> queue = new LinkedList<>();
        boolean[] visited = new boolean[MAX];
        queue.add(new State(start, ""));
        visited[start] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.number == end) {
                return current.path;
            }

            int D = (current.number * 2) % MAX;
            int S = current.number == 0 ? 9999 : current.number - 1;
            int L = (current.number % 1000) * 10 + current.number / 1000;
            int R = (current.number / 10) + (current.number % 10) * 1000;

            if (!visited[D]) {
                visited[D] = true;
                queue.add(new State(D, current.path + "D"));
            }
            if (!visited[S]) {
                visited[S] = true;
                queue.add(new State(S, current.path + "S"));
            }
            if (!visited[L]) {
                visited[L] = true;
                queue.add(new State(L, current.path + "L"));
            }
            if (!visited[R]) {
                visited[R] = true;
                queue.add(new State(R, current.path + "R"));
            }
        }
        return ""; // 이론상 여기에 도달할 일은 없습니다.
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // 테스트 케이스 개수
        StringBuilder results = new StringBuilder();

        for (int i = 0; i < T; i++) {
            int A = scanner.nextInt();
            int B = scanner.nextInt();
            results.append(bfs(A, B)).append("\n");
        }

        System.out.print(results.toString());
        scanner.close();
    }
}
