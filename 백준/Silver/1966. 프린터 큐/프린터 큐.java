import java.util.LinkedList;
import java.util.Scanner;

class Document {
    int index;
    int priority;

    public Document(int index, int priority) {
        this.index = index;
        this.priority = priority;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt(); 

        while (testCases-- > 0) {
            int N = sc.nextInt(); 
            int M = sc.nextInt(); 
            LinkedList<Document> queue = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                int priority = sc.nextInt();
                queue.add(new Document(i, priority));
            }

            int printOrder = 0; 

            while (!queue.isEmpty()) {
                Document current = queue.poll();
                boolean hasHigherPriority = false;

                for (Document doc : queue) {
                    if (doc.priority > current.priority) {
                        hasHigherPriority = true;
                        break;
                    }
                }

                if (hasHigherPriority) {
                    queue.offer(current);
                } else {
                    printOrder++;
                    if (current.index == M) {
                        System.out.println(printOrder);
                        break;
                    }
                }
            }
        }

        sc.close();
    }
}
