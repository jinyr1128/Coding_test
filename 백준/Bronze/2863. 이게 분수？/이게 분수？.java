import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 입력받기
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = scanner.nextInt();
        int D = scanner.nextInt();
        
        // 초기 표 배열
        int[][] table = {
            {A, B},
            {C, D}
        };
        
        // 최대 값을 찾기 위해 초기값 설정
        double maxValue = getTableValue(table);
        int maxRotation = 0;
        
        // 3번 회전
        for (int i = 1; i <= 3; i++) {
            table = rotateTable(table);
            double currentValue = getTableValue(table);
            if (currentValue > maxValue) {
                maxValue = currentValue;
                maxRotation = i;
            }
        }
        
        System.out.println(maxRotation);
    }
    
    // 표를 90도 회전시키는 함수
    private static int[][] rotateTable(int[][] table) {
        return new int[][] {
            {table[1][0], table[0][0]},
            {table[1][1], table[0][1]}
        };
    }
    
    // 표의 값을 계산하는 함수
    private static double getTableValue(int[][] table) {
        return (double) table[0][0] / table[1][0] + (double) table[0][1] / table[1][1];
    }
}
