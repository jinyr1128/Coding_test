import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 데이터 세트의 총 개수 입력
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        int N = Integer.parseInt(line.trim());
        
        for (int i = 0; i < N; i++) {
            // 주어, 동사, 목적어의 개수 입력 (각 줄마다 하나씩)
            // 혹시 모를 입력 사이의 공백 라인을 처리하기 위해 루프 사용
            String temp = br.readLine();
            while (temp != null && temp.trim().isEmpty()) {
                temp = br.readLine();
            }
            if (temp == null) break;
            
            int numSubjects = Integer.parseInt(temp.trim());
            int numVerbs = Integer.parseInt(br.readLine().trim());
            int numObjects = Integer.parseInt(br.readLine().trim());
            
            // 주어 리스트 입력
            String[] subjects = new String[numSubjects];
            for (int j = 0; j < numSubjects; j++) {
                subjects[j] = br.readLine();
            }
            
            // 동사 리스트 입력
            String[] verbs = new String[numVerbs];
            for (int j = 0; j < numVerbs; j++) {
                verbs[j] = br.readLine();
            }
            
            // 목적어 리스트 입력
            String[] objects = new String[numObjects];
            for (int j = 0; j < numObjects; j++) {
                objects[j] = br.readLine();
            }
            
            // 출력을 위한 StringBuilder
            StringBuilder sb = new StringBuilder();
            
            // 3중 루프를 통해 문장 조합 (이미 정렬된 상태이므로 순서대로 조합하면 됨)
            for (String s : subjects) {
                for (String v : verbs) {
                    for (String o : objects) {
                        sb.append(s).append(" ").append(v).append(" ").append(o).append(".\n");
                    }
                }
            }
            
            // 현재 데이터 세트의 결과 출력
            System.out.print(sb);
            
            // 다음 데이터 세트가 있다면 구분용 빈 줄 출력
            if (i < N - 1) {
                System.out.println();
            }
        }
    }
}