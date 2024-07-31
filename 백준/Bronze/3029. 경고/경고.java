import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 입력 받기
        String currentTime = scanner.next();
        String throwTime = scanner.next();
        
        // 현재 시간을 초 단위로 변환
        int currentSeconds = timeToSeconds(currentTime);
        // 나트륨을 던질 시간을 초 단위로 변환
        int throwSeconds = timeToSeconds(throwTime);
        
        // 기다려야 하는 시간을 계산
        int waitSeconds = throwSeconds - currentSeconds;
        
        // 기다려야 하는 시간이 음수라면, 24시간을 더해서 양수로 만들어줌
        if (waitSeconds <= 0) {
            waitSeconds += 24 * 60 * 60;
        }
        
        // 초 단위의 시간을 hh:mm:ss 형식으로 변환하여 출력
        System.out.println(secondsToTime(waitSeconds));
        
        scanner.close();
    }
    
    // hh:mm:ss 형식을 초 단위로 변환하는 메소드
    public static int timeToSeconds(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        return hours * 3600 + minutes * 60 + seconds;
    }
    
    // 초 단위의 시간을 hh:mm:ss 형식으로 변환하는 메소드
    public static String secondsToTime(int seconds) {
        int hours = seconds / 3600;
        seconds %= 3600;
        int minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
