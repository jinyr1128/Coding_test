import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // x, y, z, r, a, b, c, d (x,y,z,a,b,c,d는 계산에 불필요)
            st.nextToken(); // x
            st.nextToken(); // y
            st.nextToken(); // z
            long r = Long.parseLong(st.nextToken());
            // skip a,b,c,d
            st.nextToken(); st.nextToken(); st.nextToken(); st.nextToken();

            double area = Math.PI * (double) r * (double) r;
            sb.append(String.format("%.9f%n", area));
        }
        System.out.print(sb.toString());
    }
}
