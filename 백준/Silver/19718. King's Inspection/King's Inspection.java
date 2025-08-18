import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long a = Long.parseLong(br.readLine().trim());
        long b = Long.parseLong(br.readLine().trim());
        long c = Long.parseLong(br.readLine().trim());

        long m = Math.min(a, Math.min(b, c));
        long s = a + b + c;

        long ans = s - 3 * m;   // 최소 초
        System.out.println(ans);
    }
}
