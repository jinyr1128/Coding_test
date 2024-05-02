import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); 
        List<Member> members = new ArrayList<>(); 

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int age = Integer.parseInt(input[0]);
            String name = input[1];
            members.add(new Member(age, name)); 
        }

        Collections.sort(members, new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {
                return Integer.compare(o1.age, o2.age);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Member member : members) {
            sb.append(member.age).append(" ").append(member.name).append("\n"); 
        }

        System.out.print(sb);
    }

    static class Member {
        int age;
        String name;

        Member(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
}
