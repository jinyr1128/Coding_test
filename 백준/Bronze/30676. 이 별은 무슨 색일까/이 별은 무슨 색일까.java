import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lambda = Integer.parseInt(br.readLine().trim());  // 파장 (nm)

        String color;
        if (lambda >= 620)             color = "Red";
        else if (lambda >= 590)        color = "Orange";
        else if (lambda >= 570)        color = "Yellow";
        else if (lambda >= 495)        color = "Green";
        else if (lambda >= 450)        color = "Blue";
        else if (lambda >= 425)        color = "Indigo";
        else                           color = "Violet";  // 380–424

        System.out.println(color);
    }
}
