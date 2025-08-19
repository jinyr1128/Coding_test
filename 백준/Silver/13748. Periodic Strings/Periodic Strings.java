import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    // 길이 k 문자열을 오른쪽으로 1칸 회전: last + first..last-1
    private static String rotateRightByOne(String s) {
        int k = s.length();
        if (k <= 1) return s;
        return s.charAt(k - 1) + s.substring(0, k - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();
        int n = s.length();

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            String block = s.substring(0, k);
            String cur = block;
            boolean ok = true;

            int blocks = n / k;
            for (int i = 1; i < blocks; i++) {
                cur = rotateRightByOne(cur); // 직전 블록을 회전
                // s의 해당 구간이 cur와 같은지 확인
                if (!s.regionMatches(i * k, cur, 0, k)) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
                System.out.println(k);
                return;
            }
        }
    }
}
