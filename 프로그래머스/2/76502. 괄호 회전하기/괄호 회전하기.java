import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        Queue<Character> queue = new LinkedList<>();

        for (char c : s.toCharArray()) {
            queue.offer(c);
        }

        for (int i = 0; i < s.length(); i++) {
            if (isCorrect(queue)) {
                answer++;
            }
            queue.offer(queue.poll());
        }

        return answer;
    }

    private boolean isCorrect(Queue<Character> queue) {
        Stack<Character> stack = new Stack<>();
        for (Character c : queue) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (stack.isEmpty()) {
                return false;
            } else if (c == ')' && stack.peek() == '(') {
                stack.pop();
            } else if (c == ']' && stack.peek() == '[') {
                stack.pop();
            } else if (c == '}' && stack.peek() == '{') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
