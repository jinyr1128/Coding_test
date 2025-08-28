import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        String s = br.readLine().trim();

        long ans = 0;
        long run = 0; // 현재 연속된 '1'의 길이

        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == '1') {
                run++;
            } else {
                if (run > 0) {
                    ans += run * (run + 1) / 2;
                    run = 0;
                }
            }
        }
        // 마지막이 '1'로 끝난 경우 처리
        if (run > 0) ans += run * (run + 1) / 2;

        System.out.println(ans);
    }
}
