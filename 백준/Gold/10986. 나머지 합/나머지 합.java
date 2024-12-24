import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        long[] prefixSum = new long[N + 1];
        long[] modCount = new long[M];

        // Read the array and compute prefix sums
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + sc.nextInt();
        }

        sc.close();

        long result = 0;

        // Count mod values for prefix sums
        for (int i = 0; i <= N; i++) {
            int mod = (int) (prefixSum[i] % M);
            if (mod < 0) {
                mod += M; // Ensure mod is positive
            }

            // Add to result for each occurrence of the same mod value
            result += modCount[mod];

            // Increment the count of the current mod
            modCount[mod]++;
        }

        System.out.println(result);
    }
}
