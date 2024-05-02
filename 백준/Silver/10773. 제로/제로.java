import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int K = scanner.nextInt(); 
        Stack<Integer> stack = new Stack<>(); 

        for (int i = 0; i < K; i++) {
            int number = scanner.nextInt(); 
            if (number == 0) {
                stack.pop(); 
            } else {
                stack.push(number); 
            }
        }

        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        System.out.println(sum);
    }
}
