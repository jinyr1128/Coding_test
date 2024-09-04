import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 첫 번째 줄에 재환이가 낼 수 있는 "aaah" 입력 받기
        String jaehwan = scanner.nextLine();
        // 두 번째 줄에 의사가 요구하는 "aah" 입력 받기
        String doctor = scanner.nextLine();

        // 'a'의 개수로 비교하기
        if (jaehwan.length() >= doctor.length()) {
            System.out.println("go");
        } else {
            System.out.println("no");
        }

        scanner.close();
    }
}
