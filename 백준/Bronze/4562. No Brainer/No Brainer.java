import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력의 첫 줄에서 데이터 셋의 수를 읽어들임
        int n = sc.nextInt();

        // n개의 데이터 셋에 대해 처리
        for (int i = 0; i < n; i++) {
            int X = sc.nextInt();  // 좀비가 먹은 뇌의 수
            int Y = sc.nextInt();  // 좀비가 생존하기 위해 필요한 뇌의 수

            // 뇌의 수가 요구 조건을 충족하는지 확인
            if (X >= Y) {
                System.out.println("MMM BRAINS");
            } else {
                System.out.println("NO BRAINS");
            }
        }
        sc.close();
    }
}
