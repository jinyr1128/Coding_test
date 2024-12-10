import java.io.*;
import java.util.*;

public class Main {
    static class Event implements Comparable<Event> {
        int time;  // 이벤트 발생 시간
        int passengerIndex;  // 승객 인덱스
        int up;  // 1: 승차, -1: 하차

        public Event(int time, int passengerIndex, int up) {
            this.time = time;
            this.passengerIndex = passengerIndex;
            this.up = up;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time == other.time) {
                return Integer.compare(this.up, other.up);
            }
            return Integer.compare(this.time, other.time);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 승객 수
        Event[] events = new Event[n * 2]; // 승차/하차 이벤트 배열
        int[] stack = new int[n]; // 각 승객이 점유한 좌석 수

        // 입력 처리
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 승차 시간
            int b = Integer.parseInt(st.nextToken()); // 하차 시간
            events[i * 2] = new Event(a, i, 1); // 승차 이벤트
            events[i * 2 + 1] = new Event(b, i, -1); // 하차 이벤트
        }

        // 이벤트 정렬
        Arrays.sort(events);

        int currentCount = 0; // 현재 점유 중인 좌석 수
        int maxCount = 0; // 최대 점유 좌석 수 (policy-2)
        int globalUpCount = 0, globalDownCount = 0; // 전체 승차/하차 수 카운트

        // 이벤트 순회
        for (int i = 0; i < events.length; ) {
            int upCount = 0, downCount = 0; // 현재 이벤트에서의 승차/하차 수
            int batchTime = events[i].time; // 현재 배치의 시간

            // 동일한 시간대의 이벤트 처리
            while (i < events.length && events[i].time == batchTime) {
                currentCount += events[i].up; // 승차/하차로 인해 좌석 수 변경
                maxCount = Math.max(maxCount, currentCount); // 최대 좌석 수 갱신

                if (events[i].up > 0) upCount++; // 승차 카운트
                else downCount++; // 하차 카운트
                i++;
            }

            // 스택 정보 갱신
            for (int j = i - upCount - downCount; j < i; j++) {
                if (events[j].up > 0) {
                    stack[events[j].passengerIndex] -= globalDownCount + downCount;
                } else {
                    stack[events[j].passengerIndex] += globalUpCount;
                }
            }

            // 전체 승차/하차 카운트 갱신
            globalUpCount += upCount;
            globalDownCount += downCount;
        }

        // 결과 출력
        int s1 = Arrays.stream(stack).max().orElse(0); // policy-1
        int s2 = maxCount; // policy-2
        System.out.println(s1 + " " + s2);
    }
}
