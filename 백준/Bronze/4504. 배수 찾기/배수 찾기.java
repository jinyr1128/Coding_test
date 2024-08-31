import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt(); // 정수 n을 입력받음
        while (true) {
            int num = scanner.nextInt(); // 목록의 수를 입력받음
            if (num == 0) break; // 목록이 0으로 끝나므로, 0을 만나면 반복을 종료
            
            if (num % n == 0) {
                System.out.println(num + " is a multiple of " + n + ".");
            } else {
                System.out.println(num + " is NOT a multiple of " + n + ".");
            }
        }
        
        scanner.close();
    }
}
