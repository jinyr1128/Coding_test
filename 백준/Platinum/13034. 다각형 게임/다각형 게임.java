import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] DP = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            Set<Integer> grundySet = new HashSet<>();
            for (int j = 1; j < i; j++) {
                grundySet.add(DP[j - 1] ^ DP[i - 1 - j]);
            }
            int grundy = 0;
            while (grundySet.contains(grundy)) {
                grundy++;
            }
            DP[i] = grundy;
        }
        System.out.println(DP[n] != 0 ? 1 : 2);
    }
}
