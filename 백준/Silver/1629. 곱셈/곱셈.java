import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static long modPower(long a, long b, long c) {
        if (b == 0) return 1;  // A^0은 항상 1
        long half = modPower(a, b / 2, c);
        long result = (half * half) % c;
        if (b % 2 == 1) {
            result = (result * a) % c;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        System.out.println(modPower(A, B, C));
    }
}
