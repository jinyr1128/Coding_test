import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String infix = br.readLine();

        StringBuilder postfix = new StringBuilder();
        Stack<Character> operators = new Stack<>();

        for (char c : infix.toCharArray()) {
            if (Character.isLetter(c)) {
                // 알파벳 대문자는 바로 결과에 추가
                postfix.append(c);
            } else if (c == '(') {
                // 열린 괄호는 스택에 푸시
                operators.push(c);
            } else if (c == ')') {
                // 닫힌 괄호를 만나면 열린 괄호를 만날 때까지 스택에서 팝
                while (!operators.isEmpty() && operators.peek() != '(') {
                    postfix.append(operators.pop());
                }
                operators.pop(); // 열린 괄호 '(' 제거
            } else {
                // 연산자인 경우 우선순위를 비교하여 스택 정리
                while (!operators.isEmpty() && priority(operators.peek()) >= priority(c)) {
                    postfix.append(operators.pop());
                }
                operators.push(c);
            }
        }

        // 스택에 남은 연산자를 모두 결과에 추가
        while (!operators.isEmpty()) {
            postfix.append(operators.pop());
        }

        System.out.println(postfix.toString());
    }

    // 연산자 우선순위 반환
    private static int priority(char op) {
        if (op == '*' || op == '/') {
            return 2;
        } else if (op == '+' || op == '-') {
            return 1;
        }
        return 0;
    }
}
