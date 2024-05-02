import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String number = sc.nextLine();
            if (number.equals("0")) break; 
            
            if (isPalindrome(number)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
        
        sc.close();
    }

    private static boolean isPalindrome(String str) {
        int len = str.length();
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - 1 - i)) {
                return false; 
            }
        }
        return true; 
    }
}
