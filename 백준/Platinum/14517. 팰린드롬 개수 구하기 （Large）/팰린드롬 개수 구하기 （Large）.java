import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();
        
        int n = s.length();
        int[] dp = new int[n];
        int result = 0;
        final int MOD = 10007;
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] += 1;
                    dp[j] %= MOD;
                    for (int k = j + 1; k < n; k++) {
                        dp[j] += dp[k];
                        dp[j] %= MOD;
                    }
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            result += dp[i];
            result %= MOD;
        }
        
        System.out.println(result);
    }
}
