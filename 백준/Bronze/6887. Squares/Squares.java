import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tiles = sc.nextInt();
        int sideLength = (int) Math.sqrt(tiles); // 가장 큰 정사각형의 한 변

        System.out.println("The largest square has side length " + sideLength + ".");
    }
}
