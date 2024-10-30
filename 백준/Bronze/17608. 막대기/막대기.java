import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int max = 0, count = 0;
        Stack<Integer> stack = new Stack<>();

        int n = sc.nextInt(); // 막대기의 개수 입력
        for (int i = 0; i < n; i++) {
            int height = sc.nextInt();
            stack.push(height); // 각 막대기의 높이를 스택에 저장
        }

        // 스택을 통해 오른쪽에서 왼쪽으로 탐색
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (current > max) {
                max = current;
                count++; // 보이는 막대기의 개수 증가
            }
        }

        System.out.println(count);
        sc.close();
    }
}
