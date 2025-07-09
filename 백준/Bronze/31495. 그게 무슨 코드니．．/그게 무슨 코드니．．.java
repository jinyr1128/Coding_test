import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        // 큰따옴표 개수 세기
        int quoteCnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '"') quoteCnt++;
        }

        // 정확한 문자열인지 판별
        boolean isValid =
                quoteCnt == 2 &&                       // 큰따옴표가 정확히 2개이고
                s.length() >= 3 &&                    // 내용이 최소 1글자 이상 존재하며
                s.charAt(0) == '"' &&                 // 첫 글자가 "
                s.charAt(s.length() - 1) == '"';      // 마지막 글자가 "

        if (isValid) {
            String inner = s.substring(1, s.length() - 1);  // 큰따옴표 내부 문자열
            if (inner.isEmpty()) {                          // 내부가 빈 문자열이라면 CE
                System.out.println("CE");
            } else {
                System.out.println(inner);
            }
        } else {
            System.out.println("CE");
        }
    }
}
