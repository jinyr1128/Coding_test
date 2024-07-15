import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 입력 받기
        int N = scanner.nextInt();
        
        // 과정을 N번 거친 후 한 변의 점의 수는 (2^N + 1)
        int pointsPerSide = (int) Math.pow(2, N) + 1;
        
        // 총 점의 수는 한 변의 점의 수의 제곱
        int totalPoints = pointsPerSide * pointsPerSide;
        
        // 결과 출력
        System.out.println(totalPoints);
    }
}
