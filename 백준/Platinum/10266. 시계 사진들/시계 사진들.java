import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 360000;

    // KMP 알고리즘의 pi 배열을 구하는 메서드
    public static int[] getPi(boolean[] pattern) {
        int[] pi = new int[pattern.length];
        int j = 0;

        for (int i = 1; i < pattern.length; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                pi[i] = ++j;
            }
        }
        return pi;
    }

    // KMP 알고리즘을 이용한 패턴 매칭
    public static boolean kmp(boolean[] text, boolean[] pattern) {
        int[] pi = getPi(pattern);
        int j = 0;

        for (int i = 0; i < text.length; i++) {
            while (j > 0 && text[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if (text[i] == pattern[j]) {
                if (j == pattern.length - 1) {
                    return true; // 매칭 성공
                } else {
                    j++;
                }
            }
        }
        return false; // 매칭 실패
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        // 첫 번째 시계의 각도를 저장할 배열
        boolean[] clock1 = new boolean[MAX * 2];
        boolean[] clock2 = new boolean[MAX];

        // 첫 번째 시계 각도 입력 및 표시
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int angle = Integer.parseInt(st.nextToken());
            clock1[angle] = true;
            clock1[MAX + angle] = true; // 시계 방향으로 회전 가능성 추가
        }

        // 두 번째 시계 각도 입력 및 표시
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int angle = Integer.parseInt(st.nextToken());
            clock2[angle] = true;
        }

        // KMP 알고리즘을 사용하여 같은 시각인지 확인
        System.out.println(kmp(clock1, clock2) ? "possible" : "impossible");
    }
}
