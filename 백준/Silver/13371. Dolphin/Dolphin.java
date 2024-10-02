import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // 테스트 케이스 수 입력

        for (int t = 0; t < T; t++) {
            long n = sc.nextLong();  // n 번째 사람

            long group = 1;  // 첫 번째 그룹부터 시작
            long count = 0;  // 현재 그룹의 시작 위치

            // 해당 그룹을 찾는 루프
            while (true) {
                long groupSize = 3 * group;  // 현재 그룹의 구문 수는 3 * group
                
                if (count + groupSize >= n) {
                    break;  // 그룹을 찾았으면 루프 탈출
                }

                count += groupSize;  // 전체 카운트 증가
                group++;  // 다음 그룹으로 이동
            }

            long position = n - count;  // 그룹 내에서의 위치 계산

            if (position <= group) {
                System.out.println(group + (group == 1 ? " dolphin" : " dolphins"));
            } else if (position <= 2 * group) {
                System.out.println(group + (group == 1 ? " jump" : " jumps"));
            } else {
                System.out.println("splash");
            }
        }

        sc.close();  // 스캐너 종료
    }
}
