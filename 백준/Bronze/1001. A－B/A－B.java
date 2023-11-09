import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // 사용자 입력을 위한 Scanner 객체 생성
        
        int A = scanner.nextInt();  // 정수 A 입력
        int B = scanner.nextInt();  // 정수 B 입력
        
        System.out.println(A - B);  // A와 B의 합 출력
        
        scanner.close();  // Scanner 객체 닫기
    }
}
