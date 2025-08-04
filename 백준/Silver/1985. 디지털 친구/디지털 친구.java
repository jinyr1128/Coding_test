import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    /* 10비트 마스크로 숫자집합 계산 */
    private static int maskOf(String s) {
        int m = 0;
        for (int i = 0; i < s.length(); i++)
            m |= 1 << (s.charAt(i) - '0');
        return m;
    }

    /* x를 1회 변형해 얻을 수 있는 마스크가 yMask 와 같으면 true */
    private static boolean canTransform(String x, int yMask) {
        char[] arr = x.toCharArray();
        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {
            int a = arr[i]   - '0';
            int b = arr[i+1] - '0';

            for (int d = -1; d <= 1; d += 2) {     // d = -1 or +1
                int na = a + d, nb = b - d;        // (a±1, b∓1)

                if (na < 0 || na > 9 || nb < 0 || nb > 9) continue;
                if (i == 0 && na == 0) continue;   // 선행 0 금지

                // 새 마스크: 원본 마스크에서 a,b 빼고 na,nb 더하기
                int m = maskOfModified(x, i, a, b, na, nb);

                if (m == yMask) return true;
            }
        }
        return false;
    }

    /* 두 자리만 바꿔서 새 마스크 계산 (길이가 100 남짓이라 그대로 돌려도 충분) */
    private static int maskOfModified(String s, int idx,
                                      int a, int b, int na, int nb) {
        int m = 0;
        for (int i = 0; i < s.length(); i++) {
            int v;
            if (i == idx)         v = na;
            else if (i == idx+1)  v = nb;
            else                  v = s.charAt(i) - '0';
            m |= 1 << v;
        }
        return m;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder out = new StringBuilder();

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            String x = st.nextToken();
            String y = st.nextToken();

            int mx = maskOf(x);
            int my = maskOf(y);

            if (mx == my) {
                out.append("friends\n");
            } else if (canTransform(x, my) || canTransform(y, mx)) {
                out.append("almost friends\n");
            } else {
                out.append("nothing\n");
            }
        }
        System.out.print(out.toString());
    }
}
