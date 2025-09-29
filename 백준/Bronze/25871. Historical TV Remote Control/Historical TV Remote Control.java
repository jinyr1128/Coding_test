import java.util.Scanner;

public class Main {

    // 고장난 버튼 정보를 저장할 배열 (전역으로 선언하여 헬퍼 메소드에서 접근 용이)
    static boolean[] isBroken = new boolean[10];

    /**
     * 주어진 채널 번호를 숫자 버튼만으로 누를 수 있는지 확인하는 메소드
     * @param channel 확인할 채널 번호
     * @return 누를 수 있으면 true, 없으면 false
     */
    public static boolean isTypable(int channel) {
        // 0번 채널은 0번 버튼이 고장났는지 여부만 확인
        if (channel == 0) {
            return !isBroken[0];
        }

        // 각 자릿수를 확인
        int temp = channel;
        while (temp > 0) {
            int digit = temp % 10; // 마지막 자릿수 추출
            if (isBroken[digit]) {
                return false; // 고장난 버튼이 포함되어 있으면 false
            }
            temp /= 10; // 마지막 자릿수 제거
        }

        return true; // 모든 자릿수가 고장나지 않았으면 true
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 고장난 버튼의 개수 입력
        int n = scanner.nextInt();

        // 고장난 버튼 정보 저장
        for (int i = 0; i < n; i++) {
            int brokenDigit = scanner.nextInt();
            isBroken[brokenDigit] = true;
        }

        // 목표 채널 입력
        int targetChannel = scanner.nextInt();
        scanner.close();

        int minDifference = Integer.MAX_VALUE;

        // 0부터 999까지 입력 가능한 모든 채널을 검사
        for (int i = 0; i <= 999; i++) {
            // i번 채널을 직접 입력할 수 있는지 확인
            if (isTypable(i)) {
                // 입력 가능하다면, 목표 채널과의 거리를 계산
                int currentDifference = Math.abs(targetChannel - i);
                
                // 기존의 최소 거리보다 현재 거리가 더 작으면 갱신
                if (currentDifference < minDifference) {
                    minDifference = currentDifference;
                }
            }
        }
        
        // 찾은 최소 거리를 출력
        System.out.println(minDifference);
    }
}