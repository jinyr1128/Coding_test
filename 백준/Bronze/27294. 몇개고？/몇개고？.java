import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 입력을 받기 위한 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);

        // 첫 번째 줄에서 시간(T)과 술의 유무(S)를 공백으로 구분하여 입력받음
        int T = sc.nextInt();
        int S = sc.nextInt();

        // Scanner 사용이 끝났으므로 닫아줌
        sc.close();

        // 점심 시간이면서(12 <= T <= 16) 술을 마시지 않는 경우(S == 0)인지 확인
        if (T >= 12 && T <= 16 && S == 0) {
            // 위 조건을 만족하면 320을 출력
            System.out.println(320);
        } else {
            // 그 외 모든 경우에는 280을 출력
            System.out.println(280);
        }
    }
}