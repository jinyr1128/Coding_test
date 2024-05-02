import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); 

        for (int i = 0; i < T; i++) {
            int R = sc.nextInt();  // 반복 횟수
            String S = sc.next();  // 문자열 S 입력 받기
            StringBuilder P = new StringBuilder();

            for (char c : S.toCharArray()) {
                for (int j = 0; j < R; j++) {
                    P.append(c);  // 문자를 R번 반복하여 추가
                }
            }

            System.out.println(P.toString()); 
        }
    }
}
