import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T1 = sc.nextInt();
        int T2 = sc.nextInt();

        int maxTime = Math.min(T1, T2);
        System.out.println(maxTime);
    }
}
