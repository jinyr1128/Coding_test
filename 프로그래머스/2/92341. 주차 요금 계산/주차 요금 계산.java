import java.util.*;

class Solution {
    class Car implements Comparable<Car> {
        String carNumber;
        int totalTime;
        int fee;

        public Car(String carNumber, int totalTime, int fee) {
            this.carNumber = carNumber;
            this.totalTime = totalTime;
            this.fee = fee;
        }

        @Override
        public int compareTo(Car o) {
            return this.carNumber.compareTo(o.carNumber);
        }
    }

    public int[] solution(int[] fees, String[] records) {
        Map<String, List<Integer>> carRecords = new HashMap<>();
        for (String record : records) {
            String[] split = record.split(" ");
            int time = Integer.parseInt(split[0].split(":")[0]) * 60 + Integer.parseInt(split[0].split(":")[1]);
            String carNumber = split[1];
            String status = split[2];

            if (!carRecords.containsKey(carNumber)) {
                carRecords.put(carNumber, new ArrayList<>());
            }

            if (status.equals("IN")) {
                carRecords.get(carNumber).add(-time);
            } else {
                carRecords.get(carNumber).add(time);
            }
        }

        List<Car> cars = new ArrayList<>();
        for (String carNumber : carRecords.keySet()) {
            List<Integer> times = carRecords.get(carNumber);
            if (times.size() % 2 != 0) {
                times.add(23 * 60 + 59);
            }

            int totalTime = 0;
            for (int i = 0; i < times.size(); i += 2) {
                totalTime += times.get(i + 1) + times.get(i);
            }

            int fee = fees[1];
            if (totalTime > fees[0]) {
                fee += Math.ceil((double) (totalTime - fees[0]) / fees[2]) * fees[3];
            }

            cars.add(new Car(carNumber, totalTime, fee));
        }

        Collections.sort(cars);

        int[] answer = new int[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            answer[i] = cars.get(i).fee;
        }

        return answer;
    }
}
