import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 5줄의 입력을 받습니다.
        String[] lines = new String[5];
        for (int i = 0; i < 5; i++) {
            lines[i] = br.readLine();
        }
        
        // 문자열의 길이를 구합니다 (모든 줄의 길이는 동일함)
        int len = lines[0].length();
        
        // 결과를 저장할 5개의 StringBuilder 생성
        StringBuilder[] result = new StringBuilder[5];
        for (int i = 0; i < 5; i++) {
            result[i] = new StringBuilder();
        }
        
        // 각 열(column)을 순회하며 학생의 상태를 파악하고 다음 상태를 기록
        for (int j = 0; j < len; j++) {
            // 상태를 구분하기 위해 두 번째 줄(인덱스 1)의 문자를 확인
            char discriminator = lines[1].charAt(j);
            
            if (discriminator == 'o') {
                // 현재: 도약 준비 -> 다음: 도약 중
                result[0].append('o');
                result[1].append('w');
                result[2].append('l');
                result[3].append('n');
                result[4].append('.');
            } else if (discriminator == 'w') {
                // 현재: 도약 중 -> 다음: 도약 준비
                result[0].append('.');
                result[1].append('o');
                result[2].append('m');
                result[3].append('l');
                result[4].append('n');
            } else {
                // 현재: 착석 -> 다음: 착석 (변화 없음)
                result[0].append('.');
                result[1].append('.');
                result[2].append('o');
                result[3].append('L');
                result[4].append('n');
            }
        }
        
        // 결과 출력
        for (int i = 0; i < 5; i++) {
            System.out.println(result[i]);
        }
    }
}