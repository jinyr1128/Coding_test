import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tripNumber = 1;
        while (scanner.hasNext()) {
            double diameter = scanner.nextDouble();
            int revolutions = scanner.nextInt();
            double timeInSeconds = scanner.nextDouble();
            
            if (revolutions == 0) break;
            
            double distanceInMiles = Math.PI * diameter * revolutions / (12 * 5280);
            double timeInHours = timeInSeconds / 3600;
            double averageSpeed = distanceInMiles / timeInHours;
            
            System.out.printf("Trip #%d: %.2f %.2f%n", tripNumber, distanceInMiles, averageSpeed);
            tripNumber++;
        }
        scanner.close();
    }
}
