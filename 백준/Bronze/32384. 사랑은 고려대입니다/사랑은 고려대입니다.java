import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 스캐너로 입력받기
        Scanner sc = new Scanner(System.in);

        // 입력받은 N
        int N = sc.nextInt();

        // 결과를 저장할 StringBuilder 사용
        StringBuilder result = new StringBuilder();

        // N번 반복하며 "LoveisKoreaUniversity" 추가
        for (int i = 0; i < N; i++) {
            result.append("LoveisKoreaUniversity");
            // 마지막이 아니라면 공백 추가
            if (i < N - 1) {
                result.append(" ");
            }
        }

        // 결과 출력
        System.out.println(result);

        // 스캐너 닫기
        sc.close();
    }
}
