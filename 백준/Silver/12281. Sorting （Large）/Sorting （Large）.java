import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());

            int[] pos = new int[N];               // 입력 시 바로 저장
            StringTokenizer st = new StringTokenizer(br.readLine());
            int oddCnt = 0, evenCnt = 0;

            for (int i = 0; i < N; i++) {
                int v = Integer.parseInt(st.nextToken());
                pos[i] = v;
                if ((v & 1) == 0) evenCnt++; else oddCnt++;
            }

            /* 1. 두 소유자의 책을 분리하여 정렬 */
            int[] odds  = new int[oddCnt];
            int[] evens = new int[evenCnt];
            int oi = 0, ei = 0;
            for (int v : pos) {
                if ((v & 1) == 0) evens[ei++] = v;
                else               odds[oi++]  = v;
            }
            Arrays.sort(odds);                                   // 오름차순
            Integer[] evTmp = Arrays.stream(evens).boxed().toArray(Integer[]::new);
            Arrays.sort(evTmp, Comparator.reverseOrder());        // 내림차순
            for (int i = 0; i < evenCnt; i++) evens[i] = evTmp[i];

            /* 2. 원래의 ‘라벨’ 순서를 따라 배열 재구성 */
            int oPtr = 0, ePtr = 0;
            int[] result = new int[N];
            for (int i = 0; i < N; i++) {
                if ((pos[i] & 1) == 0)       // 짝수(=Bob)
                    result[i] = evens[ePtr++];
                else                         // 홀수(=Alex)
                    result[i] = odds[oPtr++];
            }

            /* 3. 출력 */
            out.append("Case #").append(tc).append(":");
            for (int v : result) out.append(' ').append(v);
            out.append('\n');
        }

        System.out.print(out.toString());
    }
}
