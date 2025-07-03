import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        StringBuilder result = new StringBuilder();

        char prev = 0; // 이전 문자 저장용. 초기값은 존재하지 않는 문자.

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);

            if (current != prev) {
                result.append(current);
                prev = current;
            }
        }

        System.out.println(result.toString());
        sc.close();
    }
}
