import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        int n = Integer.parseInt(br.readLine());
        int[] sequence = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        // 순서를 뒤집음
        int[] reversedSequence = new int[n];
        for (int i = 0; i < n; i++) {
            reversedSequence[i] = sequence[n - 1 - i];
        }

        // 실패 함수 계산
        int[] pi = computePi(reversedSequence);

        // 최소 k와 p 계산
        int k = 0, p = 0, total = Integer.MAX_VALUE;
        for (int i = 0; i < pi.length; i++) {
            int currentK = pi.length - (i + 1);
            int currentP = (i + 1) - pi[i];
            if (currentK + currentP < total) {
                total = currentK + currentP;
                k = currentK;
                p = currentP;
            }
        }

        // 결과 출력
        System.out.println(k + " " + p);
    }

    private static int[] computePi(int[] pattern) {
        int n = pattern.length;
        int[] pi = new int[n];
        int j = 0;

        for (int i = 1; i < n; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                pi[i] = ++j;
            }
        }

        return pi;
    }
}
