import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        System.out.println(countSquareFree(min, max));
    }

    public static int countSquareFree(long min, long max) {
        int rangeLength = (int)(max - min + 1);
        boolean[] isSquareFree = new boolean[rangeLength];
        Arrays.fill(isSquareFree, true);

        for (long i = 2; i * i <= max; i++) {
            long square = i * i;
            // min 이상의 가장 작은 square의 배수 찾기
            long start = ((min + square - 1) / square) * square;
            for (long j = start; j <= max; j += square) {
                isSquareFree[(int)(j - min)] = false;
            }
        }

        // 제곱ㄴㄴ수의 개수 계산
        int count = 0;
        for (boolean isFree : isSquareFree) {
            if (isFree) count++;
        }
        return count;
    }
}
