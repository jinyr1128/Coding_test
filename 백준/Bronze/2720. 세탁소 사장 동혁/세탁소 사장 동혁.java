import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 테스트 케이스의 수를 입력 받음
        int T = scanner.nextInt();
        
        // 각 테스트 케이스에 대해 처리
        for (int i = 0; i < T; i++) {
            int cents = scanner.nextInt();
            int quarters = cents / 25; // 25센트(쿼터)로 나눈 몫
            cents %= 25; // 남은 센트
            
            int dimes = cents / 10; // 10센트(다임)로 나눈 몫
            cents %= 10; // 남은 센트
            
            int nickels = cents / 5; // 5센트(니켈)로 나눈 몫
            cents %= 5; // 남은 센트
            
            int pennies = cents; // 남은 센트가 페니의 개수
            
            System.out.println(quarters + " " + dimes + " " + nickels + " " + pennies);
        }
        
        scanner.close();
    }
}
