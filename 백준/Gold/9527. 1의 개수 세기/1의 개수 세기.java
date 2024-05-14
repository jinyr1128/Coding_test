import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        System.out.println(countOnesUpto(B) - countOnesUpto(A - 1));
    }

    private static long countOnesUpto(long x) {
        long count = 0;
        for (int i = 0; i < 55; i++) {
            long bit = 1L << i;
            long fullCycles = (x + 1) / (bit << 1);
            long remainder = (x + 1) % (bit << 1);
            count += fullCycles * bit;
            if (remainder > bit) {
                count += remainder - bit;
            }
        }
        return count;
    }
}
