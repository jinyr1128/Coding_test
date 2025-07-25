import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine().trim());   // 선수 수

        for (int i = 0; i < n; i++) {
            String line = br.readLine();                  // 스탯 세 개가 적힌 한 줄
            sb.append(line).append('\n');                 // 그대로 출력

            // 10 이상 스탯 개수 세기
            int cnt = 0;
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                if (Integer.parseInt(st.nextToken()) >= 10) cnt++;
            }

            // 라벨 결정
            switch (cnt) {
                case 0: sb.append("zilch");         break;
                case 1: sb.append("double");        break;
                case 2: sb.append("double-double"); break;
                case 3: sb.append("triple-double"); break;
            }
            sb.append('\n').append('\n');                // 빈 줄
        }

        System.out.print(sb.toString());
    }
}
