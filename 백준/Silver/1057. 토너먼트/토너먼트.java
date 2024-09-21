import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 참가자 수 N, 김지민의 번호, 임한수의 번호 입력
        int N = sc.nextInt();
        int kim = sc.nextInt();
        int lim = sc.nextInt();
        int round = 0;

        // 김지민과 임한수가 만나지 않을 때까지 반복
        while (kim != lim) {
            // 각 참가자의 다음 라운드 번호 계산
            kim = (kim + 1) / 2;
            lim = (lim + 1) / 2;
            round++;
        }

        // 결과 출력
        System.out.println(round);

        sc.close();
    }
}
