import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받기
        int A = scanner.nextInt();
        int I = scanner.nextInt();
        
        // 저작권이 있는 멜로디의 최소 개수 계산
        int minimumMelodies = (A * I) - (A - 1);
        
        // 결과 출력
        System.out.println(minimumMelodies);
    }
}
