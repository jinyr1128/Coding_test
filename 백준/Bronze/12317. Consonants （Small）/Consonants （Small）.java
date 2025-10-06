import java.util.Scanner;

public class Main {

    // 주어진 문자가 모음인지 확인하는 헬퍼(helper) 메소드
    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테스트 케이스의 수

        for (int caseNum = 1; caseNum <= T; caseNum++) {
            String name = sc.next();
            int n = sc.nextInt();
            int L = name.length();
            
            long nValue = 0; // n-value를 저장할 변수 (long으로 선언하여 안전성 확보)

            // 1. 부분 문자열의 시작점(i)을 0부터 L-1까지 순회
            for (int i = 0; i < L; i++) {
                int consecutiveConsonants = 0;
                // 2. 부분 문자열의 끝점(j)을 i부터 L-1까지 순회하며 확장
                for (int j = i; j < L; j++) {
                    if (!isVowel(name.charAt(j))) {
                        consecutiveConsonants++; // 자음이면 연속 카운트 증가
                    } else {
                        consecutiveConsonants = 0; // 모음이면 리셋
                    }

                    // 3. 연속된 자음의 개수가 n 이상이 되는 순간
                    if (consecutiveConsonants >= n) {
                        // 시작점이 i인 유효한 부분 문자열의 개수(L - j)를 더함
                        nValue += (L - j);
                        // 현재 시작점 i에 대한 탐색은 더 이상 필요 없으므로 중단
                        break;
                    }
                }
            }
            
            // 결과 출력
            System.out.println("Case #" + caseNum + ": " + nValue);
        }
        sc.close();
    }
}