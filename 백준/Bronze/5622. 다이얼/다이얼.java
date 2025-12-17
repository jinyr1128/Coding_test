import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.next();
        
        int totalTime = 0;
        
        // 문자열의 각 문자를 확인
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            
            switch (c) {
                case 'A': case 'B': case 'C':
                    totalTime += 3; // 숫자 2
                    break;
                case 'D': case 'E': case 'F':
                    totalTime += 4; // 숫자 3
                    break;
                case 'G': case 'H': case 'I':
                    totalTime += 5; // 숫자 4
                    break;
                case 'J': case 'K': case 'L':
                    totalTime += 6; // 숫자 5
                    break;
                case 'M': case 'N': case 'O':
                    totalTime += 7; // 숫자 6
                    break;
                case 'P': case 'Q': case 'R': case 'S':
                    totalTime += 8; // 숫자 7
                    break;
                case 'T': case 'U': case 'V':
                    totalTime += 9; // 숫자 8
                    break;
                case 'W': case 'X': case 'Y': case 'Z':
                    totalTime += 10; // 숫자 9
                    break;
            }
        }
        
        System.out.println(totalTime);
        sc.close();
    }
}