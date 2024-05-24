import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine().trim());
        Stack<Pair> stack = new Stack<>();
        long count = 0;

        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(br.readLine().trim());
            int countSameHeight = 1;

            // Remove elements from stack which are shorter than current height
            while (!stack.isEmpty() && stack.peek().height < height) {
                count += stack.peek().count;
                stack.pop();
            }

            // Handle elements with same height
            if (!stack.isEmpty() && stack.peek().height == height) {
                count += stack.peek().count;
                countSameHeight = stack.peek().count + 1;

                if (stack.size() > 1) {
                    count++;
                }

                stack.pop();
            } else if (!stack.isEmpty()) {
                count++;
            }

            stack.push(new Pair(height, countSameHeight));
        }

        System.out.println(count);
    }

    static class Pair {
        int height;
        int count;

        Pair(int height, int count) {
            this.height = height;
            this.count = count;
        }
    }
}
