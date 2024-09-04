import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break; // 입력이 0일 경우 종료
            }
            
            int totalBlocks = 0;
            for (int i = 1; i <= n; i++) {
                totalBlocks += i; // 각 층의 블록 수를 더함
            }
            
            System.out.println(totalBlocks); // 계산된 총 블록 수 출력
        }
        
        sc.close();
    }
}
