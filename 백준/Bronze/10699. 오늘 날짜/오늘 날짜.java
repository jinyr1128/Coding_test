import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        // 서울 시간대 설정
        ZoneId seoulZone = ZoneId.of("Asia/Seoul");
        // 현재 UTC 시간을 가져와서 서울 시간대로 변환
        ZonedDateTime seoulTime = ZonedDateTime.now(ZoneId.of("UTC")).withZoneSameInstant(seoulZone);
        // 날짜 출력 형식 설정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 형식에 맞게 날짜 출력
        System.out.println(formatter.format(seoulTime));
    }
}
