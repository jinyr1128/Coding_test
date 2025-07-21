import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] order = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) order[i] = Integer.parseInt(st.nextToken());

        boolean[] isFriend = new boolean[N + 1];          // 1..N
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) isFriend[Integer.parseInt(st.nextToken())] = true;

        int good = 0;                                     // 친구가 이미 앞부분에 있는 개수
        for (int i = 0; i < M; i++) if (isFriend[order[i]]) good++;

        int answer = M - good;                            // = bad
        System.out.println(answer);
    }
}
