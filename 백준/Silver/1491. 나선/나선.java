import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 동-서 길이
        int M = Integer.parseInt(st.nextToken());   // 남-북 길이

        int left = 0, right = N - 1;
        int bottom = 0, top = M - 1;
        int x = 0, y = 0;                           // 현재 위치

        while (true) {
            // 1. 동쪽
            if (bottom > top || left > right) break;
            x = right; y = bottom;
            bottom++;

            // 2. 북쪽
            if (bottom > top || left > right) break;
            x = right; y = top;
            right--;

            // 3. 서쪽
            if (bottom > top || left > right) break;
            x = left; y = top;
            top--;

            // 4. 남쪽
            if (bottom > top || left > right) break;
            x = left; y = bottom;
            left++;
        }

        System.out.println(x + " " + y);
    }
}
