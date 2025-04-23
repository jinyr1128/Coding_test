import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();        // 열의 개수
        String s = sc.next();        // 암호화된 문자열
        int c = 0;                   // 문자열 인덱스
        boolean flag = false;       // 왼→오 / 오→왼 전환 플래그

        int cycle = (s.length() + n - 1) / n;  // 행 개수
        char[][] map = new char[cycle][n];     // 지그재그 배열

        // 지그재그로 채우기
        for (int i = 0; i < cycle; i++) {
            if (!flag) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = s.charAt(c++);
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    map[i][j] = s.charAt(c++);
                }
            }
            flag = !flag;
        }

        // 세로로 읽어서 원래 문자열 복원
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < cycle; i++) {
                result.append(map[i][j]);
            }
        }

        System.out.println(result.toString());
    }
}
