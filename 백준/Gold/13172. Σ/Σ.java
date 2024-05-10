import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        long result = 0;

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long Ni = Long.parseLong(st.nextToken());
            long Si = Long.parseLong(st.nextToken());

            // Calculate the modular inverse of Ni under MOD
            long NiInverse = modularInverse(Ni, MOD);

            // Add the expected value for this die to the result
            result = (result + Si * NiInverse % MOD) % MOD;
        }

        System.out.println(result);
    }

    // Function to perform modular exponentiation. It returns (base^exponent) % MOD
    private static long modularInverse(long base, long mod) {
        return modularExponentiation(base, mod - 2, mod);
    }

    // Function to calculate x^y under modulo p in logarithmic time complexity
    private static long modularExponentiation(long base, long exponent, long mod) {
        long result = 1;
        base = base % mod;
        
        while (exponent > 0) {
            if (exponent % 2 == 1)  // If exponent is odd, multiply base with result
                result = (result * base) % mod;

            exponent = exponent >> 1;  // exponent = exponent / 2
            base = (base * base) % mod;  // base = (base * base) % mod
        }
        
        return result;
    }
}
