import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    private static class Bale implements Comparable<Bale> {
        int w, b;                 // width, breadth
        Bale(int w, int b) { this.w = w; this.b = b; }
        // width 오름차순, width 같을 일은 없음(문제 조건 "unique")
        public int compareTo(Bale o) { return Integer.compare(this.w, o.w); }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        Bale[] a = new Bale[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            a[i] = new Bale(w, b);
        }

        Arrays.sort(a);                       // 1. width 오름차순 정렬

        int[] dp = new int[N];
        int ans = 0;

        for (int i = 0; i < N; i++) {
            dp[i] = 1;                        // 최소 높이 = 자기 자신
            for (int j = 0; j < i; j++) {
                if (a[j].b < a[i].b) {        // breadth 도 더 작아야 위에 올림
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);       // 전체 최대값 갱신
        }

        System.out.println(ans);
    }
}
