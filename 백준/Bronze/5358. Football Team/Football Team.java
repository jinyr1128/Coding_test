import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String correctedLine = line
                .replace('i', '\u0001') // Temporary placeholder for 'i'
                .replace('e', 'i')
                .replace('\u0001', 'e')
                .replace('I', '\u0002') // Temporary placeholder for 'I'
                .replace('E', 'I')
                .replace('\u0002', 'E');

            System.out.println(correctedLine);
        }

        scanner.close();
    }
}
