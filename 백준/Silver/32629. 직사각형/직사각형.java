import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            long N = Long.parseLong(br.readLine().trim());

            long s = (long) Math.sqrt(N);          // floor(√N)
            while ((s + 1) * (s + 1) <= N) ++s;    // 보정
            while (s * s > N) --s;

            long best = Long.MAX_VALUE;

            for (long w = Math.max(1, s - 2); w <= s + 2; ++w) {
                long h = (N + w - 1) / w;          // ceil(N / w)
                long peri = 2 * (w + h);
                if (peri < best) best = peri;
            }
            sb.append(best).append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }
}
