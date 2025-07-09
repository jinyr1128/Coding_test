import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            boolean[] A = new boolean[10];
            boolean[] B = new boolean[10];

            // 읽어서 A 세트 표시
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int val = Integer.parseInt(st.nextToken());
                A[val] = true;
            }

            // 읽어서 B 세트 표시
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int val = Integer.parseInt(st.nextToken());
                B[val] = true;
            }

            int intersection = 0;
            int union = 0;
            for (int i = 0; i < 10; i++) {
                if (A[i] && B[i]) intersection++;
                if (A[i] || B[i]) union++;
            }

            double jaccard = intersection / (double) union;
            sb.append(jaccard > 0.5 ? '1' : '0').append('\n');
        }
        System.out.print(sb.toString());
    }
}
