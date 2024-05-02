import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n == 0) {
            System.out.println(0);
            return;
        }

        int[] opinions = new int[n];
        for (int i = 0; i < n; i++) {
            opinions[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(opinions); 

        int cutCount = (int) Math.round(n * 0.15);
        int validCount = n - 2 * cutCount;

        long sum = 0;
        for (int i = cutCount; i < n - cutCount; i++) {
            sum += opinions[i];
        }

        double average = (double) sum / validCount;
        System.out.println((int) Math.round(average));
    }
}
