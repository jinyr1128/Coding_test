import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim()); // 패턴에서의 N
        int M = Integer.parseInt(br.readLine().trim()); // 문자열 S의 길이
        String S = br.readLine().trim(); // 전체 문자열 S

        int count = 0;
        int patternCount = 0;
        int i = 0;

        // 문자열 S를 순회하면서 패턴 검사
        while (i < M - 2) { // M-2까지만 검사하여 범위 초과 방지
            // "IOI" 패턴을 찾는 조건
            if (S.charAt(i) == 'I' && S.charAt(i + 1) == 'O' && S.charAt(i + 2) == 'I') {
                patternCount++; // 패턴 카운트 증가
                if (patternCount == N) { // 원하는 "IOI...I" 패턴이 완성된 경우
                    count++; // 결과 카운트 증가
                    patternCount--; // 다음 패턴 검사를 위해 패턴 카운트 감소
                }
                i += 2; // "IO" 후 다음 "I"로 점프
            } else {
                patternCount = 0; // 패턴이 깨졌으므로 카운트 초기화
                i++; // 다음 문자로 이동
            }
        }

        System.out.println(count);
    }
}

