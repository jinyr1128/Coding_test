import java.util.Scanner;

public class Main {

    // KMP 알고리즘에서 패턴에 대한 테이블 생성
    public static int[] createKMPTable(String pattern) {
        int n = pattern.length();
        int[] table = new int[n];
        int j = 0;

        for (int i = 1; i < n; i++) {
            // 패턴이 일치하지 않으면 이전 패턴의 일치 부분으로 돌아간다.
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }

            // 패턴이 일치하면 테이블에 그 길이를 기록
            if (pattern.charAt(i) == pattern.charAt(j)) {
                table[i] = ++j;
            }
        }

        return table;
    }

    public static int findShortestAdLength(int L, String adString) {
        // KMP 테이블을 통해 반복되는 패턴 길이를 찾아낸다.
        int[] kmpTable = createKMPTable(adString);
        // L에서 가장 긴 접미사/접두사 일치 길이를 뺀 것이 반복을 제외한 실제 광고 길이
        return L - kmpTable[L - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        int L = sc.nextInt();
        String adString = sc.next();

        // 결과 계산
        int shortestLength = findShortestAdLength(L, adString);

        // 결과 출력
        System.out.println(shortestLength);

        sc.close();
    }
}
