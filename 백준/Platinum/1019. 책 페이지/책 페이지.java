import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] counts = new int[10];

        int start = 1;
        int end = N;
        int position = 1; // 현재 자릿수 (1, 10, 100, ...)

        while (start <= end) {
            // start의 끝자리가 0이 될 때까지 증가
            while (start % 10 != 0 && start <= end) {
                countNumbers(start, counts, position);
                start++;
            }

            // end의 끝자리가 9가 될 때까지 감소
            while (end % 10 != 9 && start <= end) {
                countNumbers(end, counts, position);
                end--;
            }

            if (start > end) break;

            // 이제 start는 10의 배수, end는 9로 끝남
            int total = (end / 10) - (start / 10) + 1;
            for (int i = 0; i < 10; i++) {
                counts[i] += total * position;
            }

            // 다음 자릿수로 이동
            start /= 10;
            end /= 10;
            position *= 10;
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int count : counts) {
            sb.append(count).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    // 숫자 n의 각 자릿수를 position을 고려하여 counts 배열에 계산
    private static void countNumbers(int n, int[] counts, int position) {
        while (n > 0) {
            counts[n % 10] += position;
            n /= 10;
        }
    }
}
