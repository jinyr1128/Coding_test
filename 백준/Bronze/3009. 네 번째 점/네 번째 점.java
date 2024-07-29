import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<Integer, Integer> xMap = new HashMap<>();
        HashMap<Integer, Integer> yMap = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            xMap.put(x, xMap.getOrDefault(x, 0) + 1);
            yMap.put(y, yMap.getOrDefault(y, 0) + 1);
        }

        int fourthX = 0;
        int fourthY = 0;

        for (int key : xMap.keySet()) {
            if (xMap.get(key) == 1) {
                fourthX = key;
                break;
            }
        }

        for (int key : yMap.keySet()) {
            if (yMap.get(key) == 1) {
                fourthY = key;
                break;
            }
        }

        System.out.println(fourthX + " " + fourthY);
    }
}
