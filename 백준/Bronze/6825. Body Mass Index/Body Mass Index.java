import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double weight = Double.parseDouble(sc.nextLine());
        double height = Double.parseDouble(sc.nextLine());

        double bmi = weight / (height * height);

        if (bmi > 25.0) {
            System.out.println("Overweight");
        } else if (bmi >= 18.5) {
            System.out.println("Normal weight");
        } else {
            System.out.println("Underweight");
        }
    }
}
