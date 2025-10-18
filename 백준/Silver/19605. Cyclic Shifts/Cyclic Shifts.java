import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 텍스트 T와 문자열 S를 입력받음
        String T = sc.nextLine();
        String S = sc.nextLine();
        int L = S.length();
        
        boolean found = false;

        // 1. S의 길이(L)만큼 반복하여 모든 순환 이동을 생성
        for (int i = 0; i < L; i++) {
            
            // 2. i번째 순환 이동 문자열 생성
            // S.substring(i) : i번째 인덱스부터 끝까지
            // S.substring(0, i) : 0번째 인덱스부터 i-1번째 인덱스까지
            String currentShift = S.substring(i) + S.substring(0, i);
            
            // 3. T가 현재 순환 이동 문자열을 포함하는지 확인
            if (T.contains(currentShift)) {
                found = true;
                break; // 하나라도 찾으면 더 이상 탐색할 필요 없음
            }
        }

        // 4. 결과 출력
        if (found) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
        
        sc.close();
    }
}