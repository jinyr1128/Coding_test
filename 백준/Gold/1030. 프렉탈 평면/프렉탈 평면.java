import java.io.*;

public class Main {
    static int s, n, k, r1, r2, c1, c2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] input = br.readLine().split(" ");

        s = Integer.parseInt(input[0]); // 프랙탈 생성 시간 단계
        n = Integer.parseInt(input[1]); // 한 변을 나누는 크기
        k = Integer.parseInt(input[2]); // 검정색으로 채우는 중앙 영역 크기
        r1 = Integer.parseInt(input[3]); // 출력할 행 시작
        r2 = Integer.parseInt(input[4]); // 출력할 행 끝
        c1 = Integer.parseInt(input[5]); // 출력할 열 시작
        c2 = Integer.parseInt(input[6]); // 출력할 열 끝

        int size = (int) Math.pow(n, s); // 전체 프랙탈의 크기 계산

        // r1~r2, c1~c2 범위의 프랙탈 패턴을 생성
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                sb.append(func(size, i, j));
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    /**
     * (x, y) 좌표가 흰색(0)인지 검정색(1)인지 판단하는 재귀 함수
     */
    static int func(int len, int x, int y) {
        if (len == 1) return 0; // 기본 크기이면 흰색(0)

        int unit = len / n; // 현재 프랙탈이 나눠지는 크기
        int start = (n - k) / 2 * unit;
        int end = (n + k) / 2 * unit;

        // 현재 위치가 중앙 검은색 영역에 포함되면 검은색(1)
        if (x >= start && x < end && y >= start && y < end) {
            return 1;
        }

        // 아니라면 더 작은 영역으로 재귀 호출
        return func(unit, x % unit, y % unit);
    }
}
