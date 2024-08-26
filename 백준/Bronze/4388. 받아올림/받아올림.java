import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // 두 수를 입력받음
            String a = scanner.next();
            String b = scanner.next();

            // 종료 조건: "0 0"이 입력되면 종료
            if (a.equals("0") && b.equals("0")) {
                break;
            }

            // 받아올림 계산
            int carryCount = 0;
            int carry = 0;

            // 문자열 길이를 맞추기 위해 역순으로 정렬
            int lenA = a.length();
            int lenB = b.length();

            int maxLength = Math.max(lenA, lenB);

            for (int i = 0; i < maxLength; i++) {
                // 각 자리수를 뒤에서부터 하나씩 가져옴
                int digitA = (i < lenA) ? a.charAt(lenA - 1 - i) - '0' : 0;
                int digitB = (i < lenB) ? b.charAt(lenB - 1 - i) - '0' : 0;

                // 두 수를 더하고 이전 자리에서 올라온 carry를 더함
                int sum = digitA + digitB + carry;

                // 새로 발생하는 carry가 있는지 확인
                if (sum >= 10) {
                    carry = 1;
                    carryCount++;
                } else {
                    carry = 0;
                }
            }

            // 결과 출력
            System.out.println(carryCount);
        }

        scanner.close();
    }
}
