import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int treeAmpere = sc.nextInt(); // 트리의 요구 암페어
        int watt = sc.nextInt();       // 어댑터의 와트
        int volt = sc.nextInt();       // 어댑터의 전압

        int adapterAmpere = watt / volt; // 어댑터가 공급할 수 있는 암페어 계산

        if (adapterAmpere >= treeAmpere) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

        sc.close();
    }
}
