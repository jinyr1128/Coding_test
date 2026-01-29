import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    // 키패드 매핑 배열 (인덱스를 편하게 쓰기 위해 문자열 배열로 선언)
    static String[] keyGroups = {
        "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            String line = br.readLine();
            if (line.equals("#")) break; // 종료 조건
            
            StringBuilder decryptedPassword = new StringBuilder();
            
            // 문자열의 각 문자 순회
            for (int i = 0; i < line.length(); i++) {
                char encryptedChar = line.charAt(i);
                boolean isLowerCase = Character.isLowerCase(encryptedChar);
                
                // 매핑을 위해 대문자로 변환
                char target = Character.toUpperCase(encryptedChar);
                
                // 해당 문자가 속한 그룹과 그룹 내 인덱스 찾기
                int groupIndex = -1;
                int posInGroup = -1;
                
                for (int k = 0; k < keyGroups.length; k++) {
                    int idx = keyGroups[k].indexOf(target);
                    if (idx != -1) {
                        groupIndex = k;
                        posInGroup = idx;
                        break;
                    }
                }
                
                // 복호화 로직
                // 문제에서 i는 1번째, 2번째... 순서이므로 (인덱스 + 1)만큼 시프트됨
                int shift = i + 1;
                String group = keyGroups[groupIndex];
                int len = group.length();
                
                // 원래 위치 = (현재 위치 - 시프트) % 길이
                // 음수 처리를 위해 len을 더해줌
                int originalPos = (posInGroup - shift) % len;
                if (originalPos < 0) {
                    originalPos += len;
                }
                
                char originalChar = group.charAt(originalPos);
                
                // 원래 대소문자 상태로 복구
                if (isLowerCase) {
                    decryptedPassword.append(Character.toLowerCase(originalChar));
                } else {
                    decryptedPassword.append(originalChar);
                }
            }
            
            System.out.println(decryptedPassword.toString());
        }
    }
}