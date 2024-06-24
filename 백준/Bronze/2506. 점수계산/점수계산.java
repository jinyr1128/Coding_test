import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 첫 번째 줄에서 문제의 개수를 입력 받음
        int N = scanner.nextInt();
        
        // 두 번째 줄에서 채점 결과를 입력 받음
        int[] results = new int[N];
        for (int i = 0; i < N; i++) {
            results[i] = scanner.nextInt();
        }
        
        // 총 점수를 계산할 변수
        int totalScore = 0;
        // 현재 연속적인 정답의 가산점을 계산할 변수
        int currentStreak = 0;
        
        for (int i = 0; i < N; i++) {
            if (results[i] == 1) {
                // 연속적으로 맞힌 경우 가산점 증가
                currentStreak++;
                totalScore += currentStreak;
            } else {
                // 틀린 경우 가산점 초기화
                currentStreak = 0;
            }
        }
        
        // 총 점수 출력
        System.out.println(totalScore);
        
        scanner.close();
    }
}
