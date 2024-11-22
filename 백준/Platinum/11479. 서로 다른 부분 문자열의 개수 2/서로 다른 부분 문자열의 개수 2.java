import java.io.*;
import java.util.*;

public class Main {
    static String input;
    static int[] suffixArray, lcp, group, nextGroup, ordered, count;
    static long result;

    // 접미사 배열과 LCP 계산
    static void getSuffixArrayAndLCP(String s) {
        int n = s.length();
        int p = 1; // 현재 그룹의 수
        int maxChar = Math.max(257, n + 1); // 최대 문자 코드 값

        group = new int[n + 1];
        nextGroup = new int[n + 1];
        suffixArray = new int[n];
        ordered = new int[n];
        count = new int[maxChar];

        // 초기 그룹: 각 문자를 그룹으로 설정
        for (int i = 0; i < n; i++) {
            group[i] = s.charAt(i);
        }

        // 그룹 크기를 점진적으로 증가
        for (int t = 1; t < n; t <<= 1) {
            Arrays.fill(count, 0);

            // 그룹별로 정렬
            for (int i = 0; i < n; i++) {
                count[group[Math.min(i + t, n)]]++;
            }
            for (int i = 1; i < maxChar; i++) {
                count[i] += count[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                ordered[--count[group[Math.min(i + t, n)]]] = i;
            }

            Arrays.fill(count, 0);
            for (int i = 0; i < n; i++) {
                count[group[i]]++;
            }
            for (int i = 1; i < maxChar; i++) {
                count[i] += count[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                suffixArray[--count[group[ordered[i]]]] = ordered[i];
            }

            if (p == n) break;

            p = 1;
            nextGroup[suffixArray[0]] = 1;

            for (int i = 1; i < n; i++) {
                boolean newGroup = group[suffixArray[i - 1]] != group[suffixArray[i]] ||
                                   group[suffixArray[i - 1] + t] != group[suffixArray[i] + t];
                nextGroup[suffixArray[i]] = nextGroup[suffixArray[i - 1]] + (newGroup ? 1 : 0);
                if (newGroup) p++;
            }

            System.arraycopy(nextGroup, 0, group, 0, n);
        }

        // LCP 배열 계산
        lcp = new int[n];
        for (int i = 0; i < n; i++) group[suffixArray[i]] = i;

        for (int i = 0, k = 0; i < n; i++, k = Math.max(k - 1, 0)) {
            if (group[i] == n - 1) continue;
            int j = suffixArray[group[i] + 1];
            while (i + k < n && j + k < n && s.charAt(i + k) == s.charAt(j + k)) k++;
            lcp[group[i]] = k;
            result -= k; // 중복되는 부분 문자열 개수 제거
        }

        result += (long) n * (n + 1) / 2; // 전체 부분 문자열 개수 계산
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();

        result = 0;
        getSuffixArrayAndLCP(input);

        System.out.println(result);
    }
}
