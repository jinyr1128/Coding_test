import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int q = Integer.parseInt(sc.nextLine()); // quote 개수
        List<String> quotes = new ArrayList<>();

        // quotes 저장
        for (int i = 0; i < q; i++) {
            quotes.add(sc.nextLine());
        }

        int r = Integer.parseInt(sc.nextLine()); // 조회할 rule 개수

        for (int i = 0; i < r; i++) {
            int ruleNumber = Integer.parseInt(sc.nextLine());

            if (ruleNumber >= 1 && ruleNumber <= q) {
                System.out.println("Rule " + ruleNumber + ": " + quotes.get(ruleNumber - 1));
            } else {
                System.out.println("Rule " + ruleNumber + ": No such rule");
            }
        }

        sc.close();
    }
}
