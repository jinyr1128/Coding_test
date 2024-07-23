import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // 트랜잭션 입력 받기
            int balance = scanner.nextInt();
            char transactionType = scanner.next().charAt(0);
            int amount = scanner.nextInt();

            // 종료 조건
            if (balance == 0 && transactionType == 'W' && amount == 0) {
                break;
            }

            // 트랜잭션 처리
            if (transactionType == 'W') {
                if (balance - amount < -200) {
                    System.out.println("Not allowed");
                } else {
                    balance -= amount;
                    System.out.println(balance);
                }
            } else if (transactionType == 'D') {
                balance += amount;
                System.out.println(balance);
            }
        }

        scanner.close();
    }
}
