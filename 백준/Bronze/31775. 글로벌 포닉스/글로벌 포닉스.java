import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<Character> required = new HashSet<>(Arrays.asList('l', 'k', 'p'));
        Set<Character> found = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            String s = scanner.nextLine();
            if (!s.isEmpty()) {
                found.add(s.charAt(0)); // 첫 글자만 추가
            }
        }

        if (found.containsAll(required)) {
            System.out.println("GLOBAL");
        } else {
            System.out.println("PONIX");
        }
    }
}
