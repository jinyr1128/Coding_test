import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 월과 일을 입력받음
        int month = sc.nextInt();
        int day = sc.nextInt();

        // 2월 18일과 비교
        if (month < 2 || (month == 2 && day < 18)) {
            System.out.println("Before");
        } else if (month == 2 && day == 18) {
            System.out.println("Special");
        } else {
            System.out.println("After");
        }

        sc.close();
    }
}
