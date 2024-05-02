import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = sc.nextInt();
        }

        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int numToPush = 1; 
        boolean possible = true;

        for (int target : sequence) {
            if (stack.isEmpty() || stack.peek() < target) {
                while (numToPush <= target) {
                    stack.push(numToPush++);
                    sb.append("+\n");
                }
            }
            if (stack.peek() == target) {
                stack.pop();
                sb.append("-\n");
            } else if (stack.peek() > target) {
                possible = false;
                break;
            }
        }

        if (possible) {
            System.out.println(sb);
        } else {
            System.out.println("NO");
        }

        sc.close();
    }
}
