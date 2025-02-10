import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // N 입력 받기
        int N = sc.nextInt();

        // '가'의 유니코드 값 + (N - 1) => N번째 글자 구하기
        char result = (char) ('가' + (N - 1));

        // 결과 출력
        System.out.println(result);

        sc.close();
    }
}
