import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력: 외계 숫자의 진법
        int B = Integer.parseInt(sc.nextLine());

        // 입력: 외계 숫자 체계 (각 기호와 값의 매핑)
        String[] alienDigits = sc.nextLine().split(" ");
        Map<String, Integer> alienToDecimal = new HashMap<>();
        for (int i = 0; i < B; i++) {
            alienToDecimal.put(alienDigits[i], i);
        }

        // 입력: 변환 대상 외계 숫자
        String X = sc.nextLine();

        // 외계 숫자를 10진수로 변환
        long decimalValue = 0;
        int basePower = 0; // 현재 자리수의 제곱
        List<Integer> digitValues = new ArrayList<>(); // 역순으로 값 저장

        // 외계 숫자를 역순으로 처리 (뒤에서부터 10진수 값 계산)
        while (!X.isEmpty()) {
            for (String alienDigit : alienDigits) {
                if (X.startsWith(alienDigit)) {
                    digitValues.add(alienToDecimal.get(alienDigit));
                    X = X.substring(alienDigit.length());
                    break;
                }
            }
        }

        // 자리수별 값 계산
        Collections.reverse(digitValues);
        for (int value : digitValues) {
            decimalValue += value * Math.pow(B, basePower);
            basePower++;
        }

        // 출력: 변환된 10진수 값
        System.out.println(decimalValue);
    }
}
