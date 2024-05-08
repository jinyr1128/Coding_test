import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static boolean[] truthConnected;

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        
        truthConnected = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        int knowCount = Integer.parseInt(st.nextToken());
        int[] truthKnowers = new int[knowCount];
        for (int i = 0; i < knowCount; i++) {
            truthKnowers[i] = Integer.parseInt(st.nextToken());
            truthConnected[truthKnowers[i]] = true;
        }

        List<List<Integer>> parties = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            List<Integer> party = new ArrayList<>();
            int first = Integer.parseInt(st.nextToken());
            party.add(first);
            
            for (int j = 1; j < count; j++) {
                int person = Integer.parseInt(st.nextToken());
                party.add(person);
                union(first, person);
            }
            parties.add(party);
        }

        // 진실을 아는 사람과 연결된 사람들 확인
        for (int truthKnower : truthKnowers) {
            int root = find(truthKnower);
            for (int i = 1; i <= N; i++) {
                if (find(i) == root) {
                    truthConnected[i] = true;
                }
            }
        }

        int result = 0;
        for (List<Integer> party : parties) {
            boolean canLie = true;
            for (int person : party) {
                if (truthConnected[find(person)]) {
                    canLie = false;
                    break;
                }
            }
            if (canLie) result++;
        }

        System.out.println(result);
    }
}
