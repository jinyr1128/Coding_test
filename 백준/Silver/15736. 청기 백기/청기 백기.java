import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // N이 21억까지 들어올 수 있으므로 long으로 받습니다.
        long n = sc.nextLong();
        
        // 1부터 N까지의 완전제곱수의 개수는 
        // 단순히 N의 제곱근을 구해서 소수점을 버리면 됩니다.
        // 예: N=24 -> sqrt(24) = 4.89... -> 정답 4 (1, 4, 9, 16)
        long result = (long) Math.sqrt(n);
        
        System.out.println(result);
        
        sc.close();
    }
}