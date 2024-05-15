import java.io.*;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 BufferedReader 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 테스트 케이스의 개수 입력 받기
        int T = Integer.parseInt(br.readLine());
        
        // 패턴을 정규 표현식으로 변환
        String regex = "(100+1+|01)+";
        Pattern pattern = Pattern.compile(regex);
        
        StringBuilder sb = new StringBuilder();
        
        // 각 테스트 케이스 처리
        for (int t = 0; t < T; t++) {
            String signal = br.readLine();
            
            Matcher matcher = pattern.matcher(signal);
            
            // 정규 표현식과 일치하는지 확인
            if (matcher.matches()) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        
        // 결과 출력
        System.out.print(sb.toString());
    }
}
