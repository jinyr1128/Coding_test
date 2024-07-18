import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 입력 받기
        int N = scanner.nextInt();
        
        // 점의 총 개수를 계산
        int totalPoints = 0;
        
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= i; j++) {
                totalPoints += i + j;
            }
        }
        
        // 결과 출력
        System.out.println(totalPoints);
    }
}
