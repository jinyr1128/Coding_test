import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * 백준/프로그래머스 등에서 Main 클래스로 제출해야 하는 경우 이 코드를 사용하세요.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // 입력을 빠르게 받기 위해 BufferedReader를 사용합니다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 타워 정보를 한 줄로 입력받습니다.
        String towers = br.readLine();

        // '1'을 기준으로 문자열을 나누면 연속된 '0'들의 묶음(블록)을 얻을 수 있습니다.
        // 예: "10010001" -> ["", "00", "000", ""]
        String[] zeroBlocks = towers.split("1");

        int maxDays = 0; // 모든 타워를 인증하는 데 필요한 최소 총 일수

        // '0' 블록들을 순회합니다.
        for (String block : zeroBlocks) {
            int k = block.length(); // 현재 '0' 블록의 길이

            // 블록의 길이가 0이면 (예: "11" 사이) 건너뜁니다.
            if (k == 0) {
                continue;
            }

            // 'k'개의 0을 인증하는 데 필요한 일수를 계산합니다.
            // 양쪽에서 동시에 인증이 시작되므로 하루에 2개씩 처리할 수 있습니다.
            // 필요한 일수는 k/2의 올림값이며, 정수 연산으로는 (k + 1) / 2 로 계산할 수 있습니다.
            // 예: k=5 -> (5+1)/2 = 3일
            // 예: k=4 -> (4+1)/2 = 2일
            int daysForBlock = (k + 1) / 2;

            // 현재 블록을 인증하는 데 필요한 일수가 이전에 계산된 최대 일수보다 크면
            // maxDays를 갱신합니다.
            if (daysForBlock > maxDays) {
                maxDays = daysForBlock;
            }
        }

        // 계산된 최대 일수를 출력합니다.
        System.out.println(maxDays);
    }
}