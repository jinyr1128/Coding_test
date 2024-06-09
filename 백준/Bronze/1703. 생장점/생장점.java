import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // 첫 번째 값: 나이 (연도 수)
            int age = sc.nextInt();
            if (age == 0) break; // 0이 입력되면 종료

            // 각 해별 splitting factor와 가지치기 수를 저장할 배열
            int[] splittingFactors = new int[age];
            int[] prunedBranches = new int[age];

            for (int i = 0; i < age; i++) {
                splittingFactors[i] = sc.nextInt();
                prunedBranches[i] = sc.nextInt();
            }

            // 초기 잎의 수
            int leaves = 1;

            // 각 해마다 나무의 가지 계산
            for (int i = 0; i < age; i++) {
                leaves = leaves * splittingFactors[i] - prunedBranches[i];
            }

            // 결과 출력
            System.out.println(leaves);
        }

        sc.close();
    }
}
