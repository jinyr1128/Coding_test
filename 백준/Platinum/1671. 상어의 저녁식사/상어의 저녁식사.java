import java.util.*;

public class Main {
    static class Shark {
        int size, speed, intelligence;

        public Shark(int size, int speed, int intelligence) {
            this.size = size;
            this.speed = speed;
            this.intelligence = intelligence;
        }

        public boolean canEat(Shark other) {
            return this.size >= other.size && this.speed >= other.speed && this.intelligence >= other.intelligence;
        }

        public boolean isEqual(Shark other) {
            return this.size == other.size && this.speed == other.speed && this.intelligence == other.intelligence;
        }
    }

    static int N;
    static Shark[] sharks;
    static int[] match;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 처리
        N = sc.nextInt();
        sharks = new Shark[N];
        for (int i = 0; i < N; i++) {
            int size = sc.nextInt();
            int speed = sc.nextInt();
            int intelligence = sc.nextInt();
            sharks[i] = new Shark(size, speed, intelligence);
        }

        match = new int[N];
        Arrays.fill(match, -1);

        int matched = 0;

        // 각 상어에 대해 최대 두 번의 매칭 시도
        for (int i = 0; i < N; i++) {
            for (int attempt = 0; attempt < 2; attempt++) {
                visited = new boolean[N];
                if (dfs(i)) {
                    matched++;
                }
            }
        }

        // 살아남는 상어 수 출력
        System.out.println(N - matched);
    }

    // DFS 함수
    static boolean dfs(int a) {
        visited[a] = true;

        for (int i = 0; i < N; i++) {
            if (sharks[a].isEqual(sharks[i]) && a < i) continue;
            if (i == a || !sharks[a].canEat(sharks[i])) continue;

            if (match[i] == -1 || (!visited[match[i]] && dfs(match[i]))) {
                match[i] = a;
                return true;
            }
        }
        return false;
    }
}
