import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // DP 배열 (Grundy 수 계산)
        int[] DP = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            // Set을 사용해 현재 Grundy 수를 계산
            Set<Integer> grundySet = new HashSet<>();
            for (int j = 1; j < i; j++) {
                grundySet.add(DP[j - 1] ^ DP[i - 1 - j]); // Nim-Sum 계산
            }

            // 현재 Grundy 수 찾기
            int grundy = 0;
            while (grundySet.contains(grundy)) {
                grundy++;
            }
            DP[i] = grundy;
        }

        // Grundy 수가 0이면 홍준이 승리, 그렇지 않으면 성관이 승리
        System.out.println(DP[n] != 0 ? 1 : 2);
    }
}
