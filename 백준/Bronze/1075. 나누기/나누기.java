import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long N = scanner.nextLong();
        int F = scanner.nextInt();
        scanner.close();
        
        // 마지막 두 자리를 00으로 만든 새로운 N
        long modifiedN = (N / 100) * 100;
        
        // 00부터 99까지 시도하면서 F로 나누어 떨어지는 가장 작은 값을 찾음
        int result = 0;
        for (int i = 0; i < 100; i++) {
            if ((modifiedN + i) % F == 0) {
                result = i;
                break;
            }
        }
        
        // 두 자리 수로 출력하기 위해서 포맷을 지정
        System.out.printf("%02d\n", result);
    }
}
