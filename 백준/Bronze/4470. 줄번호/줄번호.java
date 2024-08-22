import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 첫 번째 줄: 줄의 수 N을 입력받음
        int N = scanner.nextInt();
        scanner.nextLine();  // 개행 문자를 제거하기 위해 필요
        
        // N개의 줄을 입력받아 앞에 줄 번호를 붙여 출력
        for (int i = 1; i <= N; i++) {
            String line = scanner.nextLine();
            System.out.println(i + ". " + line);
        }
        
        scanner.close();
    }
}
