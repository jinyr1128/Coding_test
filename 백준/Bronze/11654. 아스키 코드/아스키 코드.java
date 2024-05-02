import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char input = sc.next().charAt(0);  
        int asciiValue = (int) input;    

        System.out.println(asciiValue);   
        sc.close();
    }
}
