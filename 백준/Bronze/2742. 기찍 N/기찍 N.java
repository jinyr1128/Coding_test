import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 자연수 N 입력 받기
        int N = scanner.nextInt();
        
        // N부터 1까지 출력
        for (int i = N; i >= 1; i--) {
            System.out.println(i);
        }
        
        scanner.close();
    }
}
