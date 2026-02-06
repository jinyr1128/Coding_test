import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 테스트 케이스의 개수 T 입력
        int T = sc.nextInt();
        
        for (int i = 0; i < T; i++) {
            // A와 B 입력 (사용하지 않아도 됨)
            long A = sc.nextLong();
            long B = sc.nextLong();
            
            // 모든 경우에 대해 변환 가능하므로 yes 출력
            System.out.println("yes");
        }
        
        sc.close();
    }
}