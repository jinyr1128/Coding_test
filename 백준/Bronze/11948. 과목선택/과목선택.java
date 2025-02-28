import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 과학 과목 점수 배열 (4개)
        int[] science = new int[4];
        for (int i = 0; i < 4; i++) {
            science[i] = sc.nextInt();
        }

        // 인문 과목 점수 입력 (2개)
        int history = sc.nextInt();
        int geography = sc.nextInt();

        // 과학 과목 정렬 후 상위 3개 선택
        Arrays.sort(science);
        int scienceSum = science[1] + science[2] + science[3]; // 상위 3개 합

        // 인문 과목 중 높은 점수 선택
        int humanitiesMax = Math.max(history, geography);

        // 최종 합 출력
        System.out.println(scienceSum + humanitiesMax);

        sc.close();
    }
}
