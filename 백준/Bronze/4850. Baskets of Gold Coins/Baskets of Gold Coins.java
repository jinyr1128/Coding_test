import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // EOF(파일의 끝)까지 반복해서 읽기
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            // 빈 줄이 들어오는 경우 예외 처리
            if (line.isEmpty()) {
                continue;
            }

            String[] tokens = line.split("\\s+");
            // 입력 데이터가 부족한 경우 예외 처리
            if (tokens.length < 4) {
                continue;
            }

            int N = Integer.parseInt(tokens[0]);  // 바구니 개수
            int w = Integer.parseInt(tokens[1]);  // 정상 무게
            int d = Integer.parseInt(tokens[2]);  // 감소된 무게 차이
            int actualWeight = Integer.parseInt(tokens[3]);  // 측정된 무게

            // 모든 바구니가 w gram이라고 가정했을 때 이론상 무게 계산
            // sum(i) for i=1..(N-1) = N*(N-1)/2
            // 따라서 S = w * (N*(N-1)/2)
            long S = (long) w * (long) N * (long) (N - 1) / 2L;

            // 실제 무게와의 차이(diff)
            long diff = S - actualWeight;

            // diff = k*d 가 되어야 하며, k == 0이면 N번 바구니가 문제
            int basket;
            if (diff == 0) {
                basket = N;
            } else {
                basket = (int)(diff / d);
            }

            System.out.println(basket);
        }

        sc.close();
    }
}
