import java.util.Scanner;

public class SwapCase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(input.chars()
                               .map(c -> Character.isLowerCase(c) ? Character.toUpperCase(c) : Character.toLowerCase(c))
                               .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                               .toString());
    }
}