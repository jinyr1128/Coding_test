import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        char ch = sc.next().charAt(0);  // 한 글자만 입력 받음

        if (ch == 'N' || ch == 'n') {
            System.out.println("Naver D2");
        } else {
            System.out.println("Naver Whale");
        }

        sc.close();
    }
}
