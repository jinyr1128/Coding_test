import java.util.Scanner;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 테스트 케이스 개수 T
        if (sc.hasNextInt()) {
            int T = sc.nextInt();
            
            while (T-- > 0) {
                int M = sc.nextInt();
                String N = sc.next(); // 숫자가 매우 클 수 있으므로 문자열로 입력받음

                if (M == 1) {
                    // Case 1: IPv8 주소 -> 부호 없는 정수
                    System.out.println(ipv8ToNum(N));
                } else {
                    // Case 2: 부호 없는 정수 -> IPv8 주소
                    System.out.println(numToIPv8(N));
                }
            }
        }
        sc.close();
    }

    // IPv8 문자열을 BigInteger로 변환하는 함수
    private static BigInteger ipv8ToNum(String ipv8) {
        // 점(.)을 기준으로 분리. regex에서 .은 특수문자이므로 \\. 사용
        String[] parts = ipv8.split("\\.");
        
        BigInteger result = BigInteger.ZERO;
        BigInteger twoFiveSix = new BigInteger("256");

        for (String part : parts) {
            // result = result * 256 + currentPart
            // 왼쪽으로 8비트 시프트(<<8) 하는 것과 동일
            result = result.multiply(twoFiveSix);
            result = result.add(new BigInteger(part));
        }
        return result;
    }

    // 숫자 문자열을 IPv8 형식으로 변환하는 함수
    private static String numToIPv8(String numStr) {
        BigInteger num = new BigInteger(numStr);
        BigInteger twoFiveSix = new BigInteger("256");
        
        List<String> parts = new ArrayList<>();

        // 총 8개의 덩어리를 만들어야 함
        for (int i = 0; i < 8; i++) {
            // 현재 값을 256으로 나눈 나머지 (= 현재 가장 오른쪽 바이트 값)
            BigInteger remainder = num.remainder(twoFiveSix);
            parts.add(remainder.toString());
            
            // 값을 256으로 나눔 (= 오른쪽으로 8비트 시프트)
            num = num.divide(twoFiveSix);
        }

        // 추출은 뒤쪽(낮은 자리)부터 되었으므로 순서를 뒤집어야 함
        Collections.reverse(parts);

        // 점(.)으로 연결하여 반환
        return String.join(".", parts);
    }
}