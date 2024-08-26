import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 테스트 케이스의 개수 T를 입력받음
        int T = scanner.nextInt();
        
        // 각 테스트 케이스 처리
        for (int i = 0; i < T; i++) {
            int n = scanner.nextInt();
            int position = 0;
            
            // 이진수에서 1의 위치를 찾음
            while (n > 0) {
                if ((n & 1) == 1) {
                    System.out.print(position + " ");
                }
                n >>= 1; // n을 오른쪽으로 1비트 이동
                position++;
            }
            System.out.println();
        }
        
        scanner.close();
    }
}
