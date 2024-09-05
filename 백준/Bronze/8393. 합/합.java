import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 입력받기
        int n = scanner.nextInt();
        
        // 1부터 n까지의 합을 계산
        int sum = (n * (n + 1)) / 2;
        
        // 결과 출력
        System.out.println(sum);
        
        scanner.close();
    }
}
