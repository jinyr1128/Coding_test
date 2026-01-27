import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. 키보드 배열을 하나의 문자열로 정의합니다.
        // 역슬래시(\)는 문자열 내에서 이스케이프 처리가 필요하므로 \\로 작성해야 합니다.
        String keyboard = "`1234567890-=QWERTYUIOP[]\\ASDFGHJKL;'ZXCVBNM,./";
        
        Scanner sc = new Scanner(System.in);
        
        // 여러 줄의 입력이 들어올 수 있으므로 hasNextLine()으로 확인합니다.
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            StringBuilder sb = new StringBuilder();
            
            // 입력받은 문자열을 한 글자씩 순회합니다.
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                
                if (ch == ' ') {
                    // 공백은 그대로 추가
                    sb.append(' ');
                } else {
                    // 키보드 문자열에서 현재 문자의 위치(index)를 찾습니다.
                    int index = keyboard.indexOf(ch);
                    
                    // 찾은 위치의 바로 왼쪽(index - 1) 문자를 추가합니다.
                    if (index != -1) {
                        sb.append(keyboard.charAt(index - 1));
                    } else {
                        // 만약 키보드에 없는 문자가 들어온다면 그대로 출력 (문제 조건상 발생하지 않음)
                        sb.append(ch);
                    }
                }
            }
            // 변환된 줄을 출력합니다.
            System.out.println(sb.toString());
        }
        
        sc.close();
    }
}