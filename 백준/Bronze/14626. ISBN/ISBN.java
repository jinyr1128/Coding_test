import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String isbn = sc.nextLine();
        sc.close();

        // * 에 들어갈 숫자(0부터 9까지)를 찾기 위한 반복문
        for (int missingDigit = 0; missingDigit <= 9; missingDigit++) {
            int sum = 0;
            String tempIsbn = isbn.replace('*', (char) (missingDigit + '0'));

            // 13자리 숫자에 대해 가중치 합을 계산
            for (int i = 0; i < 13; i++) {
                // char를 int로 변환
                int digit = tempIsbn.charAt(i) - '0';
                
                // 위치에 따른 가중치 결정 (i가 0-based 인덱스이므로, 짝수 인덱스가 홀수 번째 자리)
                if (i % 2 == 0) { // 1, 3, 5, ... 번째 자리
                    sum += digit * 1;
                } else { // 2, 4, 6, ... 번째 자리
                    sum += digit * 3;
                }
            }

            // 가중치 합이 10의 배수인지 확인
            if (sum % 10 == 0) {
                // 조건을 만족하면 해당 숫자를 출력하고 프로그램 종료
                System.out.println(missingDigit);
                return;
            }
        }
    }
}