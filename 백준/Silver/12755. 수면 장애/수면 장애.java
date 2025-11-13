import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();

        long digitLength = 1;  // 현재 확인 중인 수의 자릿수 (1, 2, 3, ...)
        long countOfNumbers = 9; // 해당 자릿수를 가진 숫자의 개수 (9, 90, 900, ...)
        long startNum = 1;       // 해당 자릿수의 시작 숫자 (1, 10, 100, ...)

        while (true) {
            // 현재 자릿수 그룹(digitLength)이 포함하는 총 숫자의 개수
            long totalDigitsInBlock = countOfNumbers * digitLength;

            // N이 현재 그룹의 총 숫자 개수보다 작거나 같으면,
            // N번째 숫자가 이 그룹 안에 있다는 의미
            if (N <= totalDigitsInBlock) {
                // 1. N을 1-based에서 0-based로 변환 (계산 편의)
                N = N - 1;

                // 2. 이 그룹의 몇 번째 숫자에 N이 포함되는지 계산
                // 예: N=15 -> N=5(0-based), digitLength=2
                //     startNum=10
                //     targetNum = 10 + (5 / 2) = 10 + 2 = 12
                long targetNum = startNum + (N / digitLength);

                // 3. 그 숫자의 몇 번째 자리인지 계산
                // 예: digit_index = 5 % 2 = 1
                long digitIndex = N % digitLength;

                // 4. 숫자를 문자열로 변환하여 해당 자리의 문자 출력
                String s = Long.toString(targetNum);
                System.out.println(s.charAt((int) digitIndex));
                
                break; // 정답을 찾았으므로 반복 종료
            }

            // N이 현재 그룹보다 크다면,
            // N에서 현재 그룹의 총 숫자 개수를 빼고 다음 자릿수로 넘어감
            N = N - totalDigitsInBlock;
            digitLength++;
            countOfNumbers *= 10;
            startNum *= 10;
        }

        sc.close();
    }
}