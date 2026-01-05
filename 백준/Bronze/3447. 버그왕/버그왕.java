import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        // 입력이 끝날 때까지(EOF) 계속 읽어들임
        while ((line = br.readLine()) != null) {
            StringBuilder sb = new StringBuilder();

            // 문자열을 한 글자씩 확인
            for (int i = 0; i < line.length(); i++) {
                sb.append(line.charAt(i));

                // 현재 sb의 길이가 3 이상이고, 끝부분이 "BUG"인지 확인
                int len = sb.length();
                if (len >= 3) {
                    // 끝에서부터 B, U, G 순서인지 확인 (가장 최근에 들어온게 G, 그 전이 U, 그 전이 B)
                    if (sb.charAt(len - 1) == 'G' && 
                        sb.charAt(len - 2) == 'U' && 
                        sb.charAt(len - 3) == 'B') {
                        
                        // "BUG"가 맞다면 마지막 3글자 삭제
                        sb.delete(len - 3, len);
                    }
                }
            }
            // 변환된 문자열 출력
            System.out.println(sb.toString());
        }
    }
}