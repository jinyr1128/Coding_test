import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Hotel {
    int bedSize, roomCapacity, availableRooms, price;
    String name;
    char bedType;

    public Hotel(int bedSize, int roomCapacity, int availableRooms, int price, String name) {
        this.bedSize = bedSize;
        this.roomCapacity = roomCapacity;
        this.availableRooms = availableRooms;
        this.price = price;
        this.name = name;

        if (bedSize >= 20 && bedSize <= 35) {
            this.bedType = 'A';
        } else if (bedSize >= 36 && bedSize <= 48) {
            this.bedType = 'B';
        } else if (bedSize >= 49 && bedSize <= 62) {
            this.bedType = 'C';
        } else {
            this.bedType = 'D';
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine().trim());
        StringBuilder output = new StringBuilder();

        for (int caseNum = 1; caseNum <= tc; caseNum++) {
            output.append("Case #").append(caseNum).append(":\n");

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 호텔 수
            int m = Integer.parseInt(st.nextToken()); // 팀 수

            Hotel[] hotels = new Hotel[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int bedSize = Integer.parseInt(st.nextToken());
                int roomCapacity = Integer.parseInt(st.nextToken());
                int availableRooms = Integer.parseInt(st.nextToken());
                int price = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                hotels[i] = new Hotel(bedSize, roomCapacity, availableRooms, price, name);
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                char preferredBedType = st.nextToken().charAt(0);
                int teamSize = Integer.parseInt(st.nextToken());
                int maxPersonsPerRoom = Integer.parseInt(st.nextToken());

                int bestPrice = Integer.MAX_VALUE;
                String bestHotel = "no-hotel";
                int selectedHotelIndex = -1;

                for (int j = 0; j < n; j++) {
                    Hotel hotel = hotels[j];

                    // 침대 타입이 맞지 않으면 무시
                    if (hotel.bedType != preferredBedType) continue;

                    // 방당 수용 인원을 계산하여 필요한 방의 개수를 결정
                    int maxPeopleInRoom = Math.min(maxPersonsPerRoom, hotel.roomCapacity);
                    int roomsNeeded = (teamSize + maxPeopleInRoom - 1) / maxPeopleInRoom;

                    // 사용할 수 있는 방의 수와 필요 방 수를 비교
                    if (hotel.availableRooms >= roomsNeeded) {
                        int totalCost = roomsNeeded * hotel.price;

                        // 비용이 더 낮은 호텔을 선택하고, 비용이 동일하면 침대 크기가 큰 호텔 선택
                        if (totalCost < bestPrice || (totalCost == bestPrice && hotel.bedSize > hotels[selectedHotelIndex].bedSize)) {
                            bestPrice = totalCost;
                            selectedHotelIndex = j;
                            bestHotel = hotel.name;
                        }
                    }
                }

                if (selectedHotelIndex != -1) {
                    output.append(bestPrice).append(" ").append(bestHotel).append("\n");
                } else {
                    output.append("no-hotel\n");
                }
            }
        }

        System.out.print(output);
    }
}
