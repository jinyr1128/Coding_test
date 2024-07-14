import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> fbiAgents = new ArrayList<>();
        
        for (int i = 1; i <= 5; i++) {
            String agentName = scanner.nextLine();
            if (agentName.contains("FBI")) {
                fbiAgents.add(i);
            }
        }

        if (fbiAgents.isEmpty()) {
            System.out.println("HE GOT AWAY!");
        } else {
            for (int agent : fbiAgents) {
                System.out.print(agent + " ");
            }
        }

        scanner.close();
    }
}
