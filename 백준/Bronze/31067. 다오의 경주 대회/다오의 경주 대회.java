import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 트랙의 수 N과 늘릴 수 있는 길이 K 입력
        int N = sc.nextInt();
        long K = sc.nextLong();

        // 각 트랙의 길이를 저장할 배열
        long[] A = new long[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextLong();
        }
        sc.close();

        // 최소 시행 횟수를 저장할 변수
        int operations = 0;

        // 첫 번째 트랙을 기준으로 시작
        // 이전 트랙의 최종 길이를 저장하는 변수
        long prevTrackLength = A[0];

        // 두 번째 트랙부터 마지막 트랙까지 순회
        for (int i = 1; i < N; i++) {
            long currentTrackLength = A[i];

            // 이전 트랙의 길이가 현재 트랙보다 크거나 같은 경우 (오름차순 위배)
            if (prevTrackLength >= currentTrackLength) {
                // 시행 횟수를 1 증가
                operations++;
                
                // 현재 트랙의 길이를 K만큼 늘림
                long newLength = currentTrackLength + K;

                // 길이를 늘려도 여전히 이전 트랙보다 작거나 같다면 불가능
                if (prevTrackLength >= newLength) {
                    System.out.println(-1);
                    return; // 프로그램 종료
                }
                
                // 다음 트랙과 비교를 위해 이전 트랙 길이 업데이트
                prevTrackLength = newLength;
            } else {
                // 오름차순을 만족하는 경우, 현재 트랙 길이를 그대로 사용
                prevTrackLength = currentTrackLength;
            }
        }

        // 모든 트랙을 확인한 후 최소 시행 횟수 출력
        System.out.println(operations);
    }
}