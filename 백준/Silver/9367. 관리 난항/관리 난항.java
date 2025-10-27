import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    // 차의 정보를 저장할 클래스
    static class Car {
        long p, q, k; // 원가, 초기 대여 비용, km당 비용

        Car(long p, long q, long k) {
            this.p = p;
            this.q = q;
            this.k = k;
        }
    }

    // 첩보원의 상태와 비용을 저장할 클래스
    static class Spy {
        boolean isRenting = false;
        long totalCost = 0; // C++의 'cost' 역할. -1이 되면 INCONSISTENT
        String rentedCarName = "";

        // Spy 객체가 처음 생성될 때의 기본값
        Spy() {}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            solve(br, sb);
        }
        System.out.print(sb.toString());
    }

    private static void solve(BufferedReader br, StringBuilder sb) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 자동차 종류 수
        int m = Integer.parseInt(st.nextToken()); // 사건 기록 수

        // 자동차 정보를 이름(String)으로 찾는 Map
        Map<String, Car> carDatabase = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String carName = st.nextToken();
            long p = Long.parseLong(st.nextToken());
            long q = Long.parseLong(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            carDatabase.put(carName, new Car(p, q, k));
        }

        // 첩보원 정보를 이름(String)으로 찾는 Map (TreeMap: 자동 사전순 정렬)
        Map<String, Spy> spyDatabase = new TreeMap<>();

        // m개의 사건 기록 처리
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String spyName = st.nextToken();
            char eventType = st.nextToken().charAt(0);

            // 첩보원이 없으면 새로 생성 (기본값: cost=0, renting=false)
            Spy spy = spyDatabase.computeIfAbsent(spyName, k -> new Spy());

            // C++: if(cost == -1) continue;
            // 첩보원이 이미 INCONSISTENT 상태(-1)이면,
            // 남은 입력을 소비하고 다음 이벤트로 넘어감
            if (spy.totalCost == -1) {
                if (eventType == 'p') st.nextToken();
                if (eventType == 'r') st.nextToken();
                if (eventType == 'a') st.nextToken();
                continue;
            }

            switch (eventType) {
                case 'p': // Pick-up
                    String carName = st.nextToken();
                    // C++: if(status) cost = -1;
                    if (spy.isRenting) { // 규칙 3 위반
                        spy.totalCost = -1;
                    } else {
                        // C++: cost += q;
                        spy.totalCost += carDatabase.get(carName).q;
                    }
                    // C++: man[name] = {true, cost, carname}; (상태는 무조건 업데이트)
                    spy.isRenting = true;
                    spy.rentedCarName = carName;
                    break;

                case 'r': // Return
                    long distance = Long.parseLong(st.nextToken());
                    // C++: if(!status) cost = -1;
                    if (!spy.isRenting) { // 규칙 1 위반
                        spy.totalCost = -1;
                    } else {
                        // C++: cost += dist * k;
                        Car c = carDatabase.get(spy.rentedCarName);
                        spy.totalCost += distance * c.k;
                    }
                    // C++: man[name] = {false, cost, ""}; (상태는 무조건 업데이트)
                    spy.isRenting = false;
                    spy.rentedCarName = "";
                    break;

                case 'a': // Accident
                    long damagePercent = Long.parseLong(st.nextToken());
                    // C++: if(!status) cost = -1;
                    if (!spy.isRenting) { // 규칙 4 위반
                        spy.totalCost = -1;
                    } else {
                        // C++: 사고 비용 계산 (올림)
                        Car c = carDatabase.get(spy.rentedCarName);
                        long p = c.p;
                        long s = damagePercent;
                        long accidentCost;
                        
                        if ((p * s) % 100 != 0) {
                            accidentCost = (p * s) / 100 + 1;
                        } else {
                            accidentCost = (p * s) / 100;
                        }
                        spy.totalCost += accidentCost;
                    }
                    // C++: man[name] = {true, cost, rent}; (상태는 변경 없음)
                    break;
            }
        }

        // 최종 점검: 규칙 2 위반 (아직 반납 안 한 첩보원)
        // C++: if(status) cost = -1;
        for (Spy spy : spyDatabase.values()) {
            if (spy.isRenting) {
                spy.totalCost = -1;
            }
        }

        // 결과 출력 (TreeMap이라 이미 이름순 정렬됨)
        for (Map.Entry<String, Spy> entry : spyDatabase.entrySet()) {
            sb.append(entry.getKey()).append(" ");
            long finalCost = entry.getValue().totalCost;
            
            if (finalCost == -1) {
                sb.append("INCONSISTENT\n");
            } else {
                sb.append(finalCost).append("\n");
            }
        }
    }
}