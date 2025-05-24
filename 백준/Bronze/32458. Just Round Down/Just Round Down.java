import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        double number = Double.parseDouble(input);
        
        // Math.floor는 double을 리턴하므로, int로 캐스팅
        int floored = (int)Math.floor(number);
        System.out.println(floored);
    }
}
