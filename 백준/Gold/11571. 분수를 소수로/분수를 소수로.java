import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 수

        StringBuilder output = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int numerator = Integer.parseInt(st.nextToken()); // 분자
            int denominator = Integer.parseInt(st.nextToken()); // 분모
            output.append(getRepeatingDecimal(numerator, denominator)).append("\n");
        }

        System.out.print(output);
    }

    // 순환 소수를 계산하는 메서드
    public static String getRepeatingDecimal(int numerator, int denominator) {
        StringBuilder result = new StringBuilder();

        // 정수 부분
        result.append(numerator / denominator);
        numerator = (numerator % denominator) * 10;

        if (numerator == 0) {
            result.append(".(0)");
            return result.toString();
        }

        result.append(".");

        // 소수 부분 계산을 위한 맵 (순환 탐지)
        HashMap<Integer, Integer> map = new HashMap<>();
        StringBuilder decimalPart = new StringBuilder();

        int index = 0;

        while (numerator != 0) {
            // 이미 나왔던 나머지가 있으면 순환이 시작됨
            if (map.containsKey(numerator)) {
                int start = map.get(numerator);
                decimalPart.insert(start, "(");
                decimalPart.append(")");
                return result.append(decimalPart).toString();
            }

            // 현재 나머지 위치 저장
            map.put(numerator, index);
            decimalPart.append(numerator / denominator);
            numerator = (numerator % denominator) * 10;
            index++;
        }

        // 순환이 없는 경우
        result.append(decimalPart).append("(0)");
        return result.toString();
    }
}
