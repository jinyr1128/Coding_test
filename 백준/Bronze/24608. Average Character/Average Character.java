import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine(); // 문자열 입력 받기
        sc.close();

        int sum = 0;

        // 문자열의 각 문자의 ASCII 값 합산
        for (int i = 0; i < s.length(); i++) {
            sum += s.charAt(i);
        }

        // 평균 값 계산 (정수형으로 버림 처리)
        int averageAscii = sum / s.length();

        // 평균 값에 해당하는 ASCII 문자 출력
        System.out.println((char) averageAscii);
    }
}
