import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 기프티콘 개수 N 입력
        String input = br.readLine();
        if (input == null) return;
        int N = Integer.parseInt(input);
        
        int count = 0;
        
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            
            // "D-" 뒷부분의 숫자만 추출하여 정수로 변환
            // 예: "D-86" -> substring(2) -> "86" -> 86
            int days = Integer.parseInt(s.substring(2));
            
            // 유효기간이 90일 이하인 경우 카운트 증가
            if (days <= 90) {
                count++;
            }
        }
        
        System.out.println(count);
    }
}