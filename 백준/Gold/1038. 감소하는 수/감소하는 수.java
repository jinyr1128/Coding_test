import java.util.*;

public class Main {
    static List<Long> decreasingNumbers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        generateDecreasingNumbers();

        if (N >= decreasingNumbers.size()) {
            System.out.println(-1);
        } else {
            System.out.println(decreasingNumbers.get(N));
        }
    }

    static void generateDecreasingNumbers() {
        Queue<Long> queue = new LinkedList<>();

        for (long i = 0; i <= 9; i++) {
            queue.offer(i);
        }

        while (!queue.isEmpty()) {
            long num = queue.poll();
            decreasingNumbers.add(num);
            long lastDigit = num % 10;

            for (long nextDigit = 0; nextDigit < lastDigit; nextDigit++) {
                long nextNum = num * 10 + nextDigit;
                queue.offer(nextNum);
            }
        }

        Collections.sort(decreasingNumbers);
    }
}
