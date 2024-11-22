import java.io.*;
import java.util.*;

public class Main {
    static final int LEN = 501;
    static List<Integer>[] adj = new ArrayList[LEN];
    static List<Vote> catVotes = new ArrayList<>();
    static List<Vote> dogVotes = new ArrayList<>();
    static boolean[] visited = new boolean[LEN];
    static int[] match = new int[LEN];
    static int T, C, D, V;

    static class Vote {
        int idx, cat, dog;

        Vote(int idx, int cat, int dog) {
            this.idx = idx;
            this.cat = cat;
            this.dog = dog;
        }
    }

    // 깊이 우선 탐색으로 이분 매칭 수행
    static boolean dfs(int x) {
        for (int t : adj[x]) {
            if (visited[t]) continue;
            visited[t] = true;
            if (match[t] == 0 || dfs(match[t])) {
                match[t] = x;
                return true;
            }
        }
        return false;
    }

    static void solve(BufferedReader br) throws IOException {
        for (int i = 0; i < LEN; i++) adj[i] = new ArrayList<>();
        catVotes.clear();
        dogVotes.clear();
        Arrays.fill(visited, false);
        Arrays.fill(match, 0);

        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());
            String pros = st.nextToken();
            String cons = st.nextToken();

            int prosIdx = Integer.parseInt(pros.substring(1));
            int consIdx = Integer.parseInt(cons.substring(1));

            if (pros.charAt(0) == 'C') {
                catVotes.add(new Vote(i, prosIdx, consIdx));
            } else {
                dogVotes.add(new Vote(i, consIdx, prosIdx));
            }
        }

        // 간선 생성
        for (Vote cv : catVotes) {
            for (Vote dv : dogVotes) {
                if (cv.cat == dv.cat || cv.dog == dv.dog) {
                    adj[cv.idx].add(dv.idx);
                }
            }
        }

        // 이분 매칭 수행
        int count = 0;
        for (int i = 1; i <= V; i++) {
            Arrays.fill(visited, false);
            if (dfs(i)) count++;
        }

        System.out.println(V - count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            solve(br);
        }
    }
}
