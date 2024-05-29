import java.util.*;
import java.io.*;

class Person {
    int reserveTime; // 예약 시간
    int arriveTime; // 도착 시간
    
    Person(int reserveTime, int arriveTime) {
        this.reserveTime = reserveTime;
        this.arriveTime = arriveTime;
    }
}

public class Main {

    static int n;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        Map<Integer, Person> hashMap = new LinkedHashMap<>();
        PriorityQueue<Person> personList = new PriorityQueue<>(new Comparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                if (o1.arriveTime == o2.arriveTime) {
                    return o1.reserveTime - o2.reserveTime;
                }
                return o1.arriveTime - o2.arriveTime;
            }
        });
        
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());
            
            personList.add(new Person(t1, t2));
        }
        
        int time = 0;
        int result = 0;
        while (!personList.isEmpty() || !hashMap.isEmpty()) {
            time++;
            
            if (!personList.isEmpty()) {
                int arrivalTime = personList.peek().arriveTime;
                // 사람리스트의 사람이 도착한 시각이 현재시각과 같은 경우
                if (time == arrivalTime) {
                    
                    while (!personList.isEmpty() && arrivalTime == personList.peek().arriveTime) {
                        Person person = personList.poll();
                        hashMap.put(person.reserveTime, person);
                    }
                }
                
            }
            
            if (!hashMap.isEmpty() && hashMap.containsKey(time)) {
                Person p = hashMap.get(time);
                result = Math.max(result, time - p.arriveTime);
                hashMap.remove(time);
                
            } else if (!hashMap.isEmpty()) {
                
                Iterator<Integer> it = hashMap.keySet().iterator();
                if (it.hasNext()) {
                    int key = it.next();
                    result = Math.max(result, time - hashMap.get(key).arriveTime);
                    hashMap.remove(key);
                    continue;
                }
            }
        }
        System.out.println(result);
    }
}
