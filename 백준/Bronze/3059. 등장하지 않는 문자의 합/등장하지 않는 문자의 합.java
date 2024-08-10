import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 입력 데이터의 수
        int T = scanner.nextInt();
        scanner.nextLine();  // 개행 문자 소비
        
        // 모든 알파벳의 아스키 코드 합 (65부터 90까지)
        int totalAsciiSum = 0;
        for (int i = 65; i <= 90; i++) {
            totalAsciiSum += i;
        }

        // 각 테스트 케이스 처리
        for (int t = 0; t < T; t++) {
            String S = scanner.nextLine();
            int presentAsciiSum = 0;
            
            // 문자열에 등장하는 알파벳의 아스키 코드 합 계산
            Set<Character> seen = new HashSet<>();
            for (char c : S.toCharArray()) {
                if (!seen.contains(c)) {
                    presentAsciiSum += (int) c;
                    seen.add(c);
                }
            }

            // 등장하지 않는 알파벳의 아스키 코드 합
            int missingAsciiSum = totalAsciiSum - presentAsciiSum;
            System.out.println(missingAsciiSum);
        }

        scanner.close();
    }
}
