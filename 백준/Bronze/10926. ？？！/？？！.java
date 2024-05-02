import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String id = scanner.nextLine();
        
        String surprisedId = id + "??!";

        System.out.println(surprisedId);

        scanner.close();
    }
}
