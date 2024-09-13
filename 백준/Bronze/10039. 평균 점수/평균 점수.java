import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;

        // 5명의 점수를 입력받고 처리
        for (int i = 0; i < 5; i++) {
            int score = scanner.nextInt();
            
            // 점수가 40점 미만이면 40점으로 처리
            if (score < 40) {
                score = 40;
            }
            
            totalScore += score;
        }

        // 평균 계산
        int averageScore = totalScore / 5;
        
        // 평균 점수 출력
        System.out.println(averageScore);

        scanner.close();
    }
}
