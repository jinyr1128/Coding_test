import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sum = scanner.nextInt();
        int diff = scanner.nextInt();
        
        // x = (합 + 차) / 2, y = (합 - 차) / 2
        if ((sum + diff) % 2 != 0 || (sum - diff) % 2 != 0 || sum < diff) {
            System.out.println(-1);
        } else {
            int x = (sum + diff) / 2;
            int y = (sum - diff) / 2;
            
            // 점수는 항상 음이 아닌 정수이어야 함
            if (x < 0 || y < 0) {
                System.out.println(-1);
            } else {
                // x가 더 크면 x y 순으로 출력, 그렇지 않으면 y x 순으로 출력
                if (x >= y) {
                    System.out.println(x + " " + y);
                } else {
                    System.out.println(y + " " + x);
                }
            }
        }
        
        scanner.close();
    }
}
