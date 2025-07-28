import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        long[] x = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) x[i] = Long.parseLong(st.nextToken());

        Arrays.sort(x);                     // ① 정렬  O(n log n)

        long prefixSum = 0;                 // Σ x[0..i-1]
        long halfSum   = 0;                 // Σ_{i>j} (x[i]-x[j])

        for (int i = 0; i < n; i++) {       // ② 한 번 순회  O(n)
            halfSum  += (long) i * x[i] - prefixSum;
            prefixSum += x[i];
        }

        long answer = halfSum * 2;          // 순서쌍은 두 배
        System.out.println(answer);
    }
}
