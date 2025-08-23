import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine().trim();
        int n = S.length();

        // 접두사 합: ps[i] = S[0..i-1]의 자리수 합
        int[] ps = new int[n + 1];
        for (int i = 0; i < n; i++) {
            ps[i + 1] = ps[i] + (S.charAt(i) - '0');
        }

        // 가장 긴 짝수 길이부터 탐색
        for (int len = (n % 2 == 0 ? n : n - 1); len >= 2; len -= 2) {
            for (int start = 0; start + len <= n; start++) {
                int mid = start + len / 2;
                int sumLeft = ps[mid] - ps[start];
                int sumRight = ps[start + len] - ps[mid];
                if (sumLeft == sumRight) {
                    System.out.println(len);
                    return;
                }
            }
        }

        System.out.println(0);
    }
}
