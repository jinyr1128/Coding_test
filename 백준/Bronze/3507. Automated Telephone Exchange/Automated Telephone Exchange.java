import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // ATE의 인덱스 n 입력
        int n = sc.nextInt();
        int luckyCount = 0;  // 행운의 전화번호 개수
        
        // 두 자리 숫자의 모든 조합 (XX-XX) 순회
        for (int firstPart = 0; firstPart <= 99; firstPart++) {
            for (int secondPart = 0; secondPart <= 99; secondPart++) {
                // n - firstPart - secondPart가 0이 되는지 확인
                if (n - firstPart - secondPart == 0) {
                    luckyCount++;  // 행운의 전화번호 발견 시 카운트 증가
                }
            }
        }
        
        // 행운의 전화번호 개수 출력
        System.out.println(luckyCount);
    }
}
