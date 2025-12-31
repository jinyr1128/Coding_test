import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. 암호문 입력
        String text = sc.next();
        
        int days = 0;
        
        // 2. 문자열 순회하며 비교
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            
            // i번째 자리에 와야 할 'PER'의 문자 결정
            // 0 -> P, 1 -> E, 2 -> R
            if (i % 3 == 0) {
                if (currentChar != 'P') days++;
            } else if (i % 3 == 1) {
                if (currentChar != 'E') days++;
            } else { // i % 3 == 2
                if (currentChar != 'R') days++;
            }
        }
        
        // 3. 결과 출력
        System.out.println(days);
        
        sc.close();
    }
}