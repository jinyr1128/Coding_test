import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); 
        sc.nextLine(); 
        
        for (int i = 0; i < T; i++) {
            String s = sc.nextLine();
            System.out.println(isValidParentheses(s) ? "YES" : "NO");
        }
        sc.close();
    }

    public static boolean isValidParentheses(String s) {
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                balance++; 
            } else if (s.charAt(i) == ')') {
                balance--; 
                if (balance < 0) {
                    return false; 
                }
            }
        }
        return balance == 0; 
    }
}
