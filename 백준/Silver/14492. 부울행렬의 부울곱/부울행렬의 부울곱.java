import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.BitSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        /* --- A의 행, B의 열을 비트셋으로 저장 --- */
        BitSet[] rowA = new BitSet[N];
        BitSet[] colB = new BitSet[N];
        for (int i = 0; i < N; i++) { rowA[i] = new BitSet(N); }
        for (int j = 0; j < N; j++) { colB[j] = new BitSet(N); }

        /* A 읽기 */
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1)
                    rowA[i].set(j);
            }
        }

        /* B 읽으면서 열 비트셋 구축 */
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1)
                    colB[j].set(i);      // 열 j에서 행 i에 1
            }
        }

        /* --- 부울곱 후 1의 개수 세기 --- */
        long cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (rowA[i].intersects(colB[j])) cnt++;
            }
        }

        System.out.println(cnt);
    }
}
