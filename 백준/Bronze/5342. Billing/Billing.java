import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        /* 1. 물품-가격 사전 */
        Map<String, BigDecimal> price = new HashMap<>();
        price.put("Paper",     new BigDecimal("57.99"));
        price.put("Printer",   new BigDecimal("120.50"));
        price.put("Planners",  new BigDecimal("31.25"));
        price.put("Binders",   new BigDecimal("22.50"));
        price.put("Calendar",  new BigDecimal("10.95"));
        price.put("Notebooks", new BigDecimal("11.20"));
        price.put("Ink",       new BigDecimal("66.95"));

        /* 2. 입력 & 합계 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigDecimal total = BigDecimal.ZERO;

        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.equals("EOI")) break;          // 입력 종료
            BigDecimal cost = price.get(line);      // 항상 유효한 항목만 입력된다고 가정
            total = total.add(cost);
        }

        /* 3. 출력: $ + 소수점 둘째자리까지 */
        System.out.printf("$%.2f%n", total);
    }
}
