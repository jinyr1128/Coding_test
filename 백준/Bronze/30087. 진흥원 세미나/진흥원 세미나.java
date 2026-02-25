import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입출력 속도 향상을 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. 세미나와 교실 번호를 매핑할 HashMap 생성 및 초기화
        HashMap<String, String> seminarMap = new HashMap<>();
        seminarMap.put("Algorithm", "204");
        seminarMap.put("DataAnalysis", "207");
        seminarMap.put("ArtificialIntelligence", "302");
        seminarMap.put("CyberSecurity", "B101");
        seminarMap.put("Network", "303");
        seminarMap.put("Startup", "501");
        seminarMap.put("TestStrategy", "105");
        
        // 2. 신청한 세미나 수 N 입력
        String line = br.readLine();
        if (line == null) return;
        int N = Integer.parseInt(line.trim());
        
        StringBuilder sb = new StringBuilder();
        
        // 3. N개의 세미나 이름을 입력받아 해당하는 교실 번호 찾기
        for (int i = 0; i < N; i++) {
            String seminar = br.readLine().trim();
            sb.append(seminarMap.get(seminar)).append("\n");
        }
        
        // 4. 결과 출력
        System.out.print(sb);
    }
}