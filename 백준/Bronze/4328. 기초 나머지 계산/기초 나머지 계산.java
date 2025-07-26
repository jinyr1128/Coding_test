import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    /* p·mod·m :  p·(base-b 문자열) → (int)rem */
    private static int mod(String p, int b, int M) {
        int rem = 0;
        for (int i = 0; i < p.length(); i++) {
            int digit = p.charAt(i) - '0';          // 0‥b-1
            rem = (rem * b + digit) % M;
        }
        return rem;
    }

    /* 10진수 val (0 ≤ val < M ≤ 10^9)  →  base-b 문자열 */
    private static String toBase(int val, int b) {
        if (val == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (val > 0) {
            sb.append((char) ('0' + (val % b)));
            val /= b;
        }
        return sb.reverse().toString();
    }

    /* m (base-b 문자열) → 10진수 int */
    private static int parseBase(String m, int b) {
        int v = 0;
        for (int i = 0; i < m.length(); i++) {
            v = v * b + (m.charAt(i) - '0');
        }
        return v;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        while (true) {
            String line = br.readLine();
            if (line == null) break;
            line = line.trim();
            if (line.equals("0")) break;            // 종료

            StringTokenizer st = new StringTokenizer(line);
            int b = Integer.parseInt(st.nextToken());       // base
            String pStr = st.nextToken();                   // dividend (base-b)
            String mStr = st.nextToken();                   // divisor  (base-b)

            int M = parseBase(mStr, b);                     // 10진수 값
            if (M == 0) {                  // m == 0 은 정의되지 않지만 안전 처리
                out.append("0\n");
                continue;
            }

            int rem = mod(pStr, b, M);                      // p mod M  (10진수)
            out.append(toBase(rem, b)).append('\n');
        }
        System.out.print(out.toString());
    }
}
