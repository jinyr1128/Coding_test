import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 8x8 체스판 입력 받기
        char[][] board = new char[8][8];
        for (int i = 0; i < 8; i++) {
            board[i] = sc.nextLine().toCharArray();
        }
        
        int count = 0;  // 하얀 칸 위의 말을 세기 위한 변수
        
        // 체스판 순회
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // (i + j)가 짝수인 경우 하얀 칸임
                if ((i + j) % 2 == 0 && board[i][j] == 'F') {
                    count++;  // 하얀 칸 위에 말이 있으면 카운트 증가
                }
            }
        }
        
        // 결과 출력
        System.out.println(count);
        
        sc.close();
    }
}
