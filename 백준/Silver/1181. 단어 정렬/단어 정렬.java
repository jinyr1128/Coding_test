import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); 
        Set<String> words = new HashSet<>(); 

        for (int i = 0; i < N; i++) {
            words.add(sc.next()); 
        }

        List<String> sortedWords = new ArrayList<>(words);
        
        Collections.sort(sortedWords, (word1, word2) -> {
            if (word1.length() == word2.length()) {
                return word1.compareTo(word2); 
            } else {
                return Integer.compare(word1.length(), word2.length()); 
            }
        });

        sortedWords.forEach(System.out::println);

        sc.close();
    }
}
