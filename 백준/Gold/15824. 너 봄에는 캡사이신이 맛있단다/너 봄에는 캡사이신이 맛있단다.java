import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[n];
        long[] two = new long[n];
        long MOD = 1_000_000_007;

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
        }

        Arrays.sort(arr);

        long temp = 1;
        for (int i = 0; i < n; i++) {
            two[i] = temp - 1;
            temp = (temp * 2) % MOD;
        }

        long p = 0, m = 0;
        for (int i = 0; i < n; i++) {
            p = (p + two[i] * arr[i] % MOD) % MOD;
            m = (m + two[i] * arr[n - 1 - i] % MOD) % MOD;
        }

        System.out.println((p + MOD - m) % MOD);
    }
}
