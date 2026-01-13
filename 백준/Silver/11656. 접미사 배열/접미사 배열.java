import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. 문자열 S 입력
        String s = br.readLine();
        int len = s.length();
        
        // 2. 모든 접미사를 저장할 배열 생성
        String[] suffixes = new String[len];
        
        // 3. 접미사 생성 (substring 활용)
        for (int i = 0; i < len; i++) {
            suffixes[i] = s.substring(i);
        }
        
        // 4. 사전순 정렬
        Arrays.sort(suffixes);
        
        // 5. 결과 출력
        StringBuilder sb = new StringBuilder();
        for (String suffix : suffixes) {
            sb.append(suffix).append('\n');
        }
        System.out.print(sb);
    }
}