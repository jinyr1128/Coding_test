import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().trim();
        
        // 문자열을 #으로 구분하여 새로운 문자열 생성
        StringBuilder sb = new StringBuilder("#");
        for (char c : input.toCharArray()) {
            sb.append(c).append("#");
        }
        String modifiedString = sb.toString();

        System.out.println(manachers(modifiedString));
    }

    public static long manachers(String s) {
        int n = s.length();
        int[] A = new int[n]; // 각 중심에서 확장되는 회문의 길이를 저장하는 배열
        int r = 0, p = 0;     // r: 가장 오른쪽까지 뻗은 회문의 끝, p: 그 중심
        long result = 0;      // 총 회문 개수

        for (int i = 0; i < n; i++) {
            // 회문의 길이 대칭성 활용
            if (i <= r) {
                A[i] = Math.min(A[2 * p - i], r - i);
            }

            // 회문 확장
            while (i - A[i] - 1 >= 0 && i + A[i] + 1 < n && s.charAt(i - A[i] - 1) == s.charAt(i + A[i] + 1)) {
                A[i]++;
            }

            // 가장 오른쪽 회문 업데이트
            if (r < i + A[i]) {
                r = i + A[i];
                p = i;
            }

            // 각 중심에서 구해진 회문의 절반 크기만큼 회문 개수를 누적
            result += (A[i] + 1) / 2;
        }
        
        return result;
    }
}
