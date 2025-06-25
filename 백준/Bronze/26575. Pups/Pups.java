import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /* 첫 줄: 테스트케이스 개수 */
        int n = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            /* 세 값 모두 실수로 읽는다 (d 역시 3 또는 3.0 모두 허용) */
            double d = Double.parseDouble(st.nextToken()); // 강아지 수
            double f = Double.parseDouble(st.nextToken()); // 강아지당 사료량(lb)
            double p = Double.parseDouble(st.nextToken()); // 파운드당 가격($)

            double total = d * f * p;                      // 총 금액
            sb.append(String.format("$%.2f%n", total));    // 둘째 자리까지 반올림
        }

        System.out.print(sb.toString());
    }
}
