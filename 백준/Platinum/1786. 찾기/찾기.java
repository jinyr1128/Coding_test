import java.io.*;
import java.util.*;

public class Main {
    // KMP 알고리즘을 사용하여 패턴을 찾는 함수
    public static List<Integer> kmpSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] lps = computeLPSArray(pattern);
        List<Integer> result = new ArrayList<>();
        
        int i = 0; // 텍스트를 위한 인덱스
        int j = 0; // 패턴을 위한 인덱스
        
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            if (j == m) {
                result.add(i - j + 1); // 패턴이 발견된 위치를 추가 (1-based index)
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return result;
    }
    
    // 패턴의 LPS 배열을 계산하는 함수
    public static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;
        lps[0] = 0;
        
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String text = br.readLine();
        String pattern = br.readLine();
        
        List<Integer> matches = kmpSearch(text, pattern);
        
        System.out.println(matches.size());
        for (int match : matches) {
            System.out.print(match + " ");
        }
    }
}
