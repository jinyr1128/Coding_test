import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = Integer.parseInt(sc.nextLine());  // 문제 개수 입력

        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();

            if (line.equals("P=NP")) {
                System.out.println("skipped");
            } else {
                String[] parts = line.split("\\+");  // + 기준으로 split
                int a = Integer.parseInt(parts[0]);
                int b = Integer.parseInt(parts[1]);
                System.out.println(a + b);
            }
        }

        sc.close();
    }
}
