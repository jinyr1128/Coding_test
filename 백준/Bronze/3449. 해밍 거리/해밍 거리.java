import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 테스트 케이스 수 입력
        int T = sc.nextInt();
        sc.nextLine();  // 개행 문자 처리
        
        // 테스트 케이스 순회
        for (int i = 0; i < T; i++) {
            // 두 이진수 입력
            String binary1 = sc.nextLine();
            String binary2 = sc.nextLine();
            
            // 해밍 거리 계산
            int hammingDistance = 0;
            for (int j = 0; j < binary1.length(); j++) {
                if (binary1.charAt(j) != binary2.charAt(j)) {
                    hammingDistance++;
                }
            }
            
            // 결과 출력
            System.out.println("Hamming distance is " + hammingDistance + ".");
        }
        
        sc.close();
    }
}
