import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            sb.append(findClosestArea(a, b, c)).append("\n");
        }

        System.out.print(sb.toString());
    }

    // 주어진 A*B 영역에 가장 가까운 C*C 크기 직사각형 영역 찾기
    public static long findClosestArea(long a, long b, long c) {
        long targetArea = a * b; // 목표 면적
        long squareArea = c * c; // 한 개의 사각형 면적

        // p는 targetArea보다 작거나 같은 최대의 C*C 배수
        long p = (targetArea / squareArea) * squareArea;
        // q는 targetArea보다 크거나 같은 최소의 C*C 배수
        long q = ((targetArea + squareArea - 1) / squareArea) * squareArea;

        // p와 q 중에서 targetArea와의 차이가 적은 값을 선택
        return (p > 0 && targetArea - p < q - targetArea) ? p : q;
    }
}
