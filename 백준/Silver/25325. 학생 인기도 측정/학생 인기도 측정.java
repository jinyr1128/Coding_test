import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    // 학생 정보를 저장하고 정렬하기 위한 클래스
    static class Student implements Comparable<Student> {
        String name;
        int likeCount;

        public Student(String name, int likeCount) {
            this.name = name;
            this.likeCount = likeCount;
        }

        @Override
        public int compareTo(Student other) {
            // 1. 인기도(likeCount) 내림차순 (큰 수가 먼저)
            if (this.likeCount != other.likeCount) {
                return other.likeCount - this.likeCount;
            }
            // 2. 인기도가 같다면 이름(name) 오름차순 (사전순)
            return this.name.compareTo(other.name);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. 학생 수 N 입력
        int n = Integer.parseInt(br.readLine());
        
        // 2. 학생 이름 목록 입력 및 맵 초기화
        Map<String, Integer> scoreMap = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 입력받은 학생들로 맵을 초기화 (아직 득표수는 0)
        while (st.hasMoreTokens()) {
            scoreMap.put(st.nextToken(), 0);
        }

        // 3. 각 학생이 좋아하는 목록을 순회하며 점수 집계
        // 총 N명의 학생이 투표를 함
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            st = new StringTokenizer(line);
            
            while (st.hasMoreTokens()) {
                String likedStudent = st.nextToken();
                // 해당 학생의 득표수 증가
                if (scoreMap.containsKey(likedStudent)) {
                    scoreMap.put(likedStudent, scoreMap.get(likedStudent) + 1);
                }
            }
        }

        // 4. 정렬을 위해 리스트로 변환
        List<Student> studentList = new ArrayList<>();
        for (String name : scoreMap.keySet()) {
            studentList.add(new Student(name, scoreMap.get(name)));
        }

        // 5. 조건에 맞춰 정렬 (compareTo 활용)
        Collections.sort(studentList);

        // 6. 결과 출력
        StringBuilder sb = new StringBuilder();
        for (Student s : studentList) {
            sb.append(s.name).append(" ").append(s.likeCount).append("\n");
        }
        System.out.print(sb);
    }
}