import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int totalScore = 0; // 총점을 저장할 변수

        // 5개의 점수를 입력받아 합산
        for (int i = 0; i < 5; i++) {
            totalScore += sc.nextInt();
        }

        // 총점 출력
        System.out.println(totalScore);

        sc.close();
    }
}
