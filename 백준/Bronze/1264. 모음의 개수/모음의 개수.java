import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();
            if (line.equals("#")) {
                break;
            }

            int vowelCount = 0;
            for (char c : line.toCharArray()) {
                if (isVowel(c)) {
                    vowelCount++;
                }
            }

            System.out.println(vowelCount);
        }
    }

    private static boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
