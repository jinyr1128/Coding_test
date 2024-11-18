import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 장르별 총 재생 횟수를 저장
        Map<String, Integer> genrePlayCount = new HashMap<>();
        // 장르별 노래 리스트를 저장
        Map<String, List<Song>> genreSongs = new HashMap<>();

        // 데이터 구성
        for (int i = 0; i < genres.length; i++) {
            genrePlayCount.put(genres[i], genrePlayCount.getOrDefault(genres[i], 0) + plays[i]);
            genreSongs.computeIfAbsent(genres[i], k -> new ArrayList<>()).add(new Song(i, plays[i]));
        }

        // 장르별 총 재생 횟수 기준으로 정렬
        List<String> sortedGenres = new ArrayList<>(genrePlayCount.keySet());
        sortedGenres.sort((a, b) -> genrePlayCount.get(b) - genrePlayCount.get(a));

        // 결과 리스트 생성
        List<Integer> result = new ArrayList<>();

        for (String genre : sortedGenres) {
            // 장르 내 노래를 재생 횟수, 고유 번호 순으로 정렬
            List<Song> songs = genreSongs.get(genre);
            songs.sort((a, b) -> {
                if (b.plays == a.plays) return a.id - b.id; // 재생 횟수 같으면 고유 번호 순
                return b.plays - a.plays; // 재생 횟수 기준 내림차순
            });

            // 장르 내 상위 2곡 추가
            for (int i = 0; i < Math.min(2, songs.size()); i++) {
                result.add(songs.get(i).id);
            }
        }

        // 결과를 배열로 변환
        return result.stream().mapToInt(i -> i).toArray();
    }

    static class Song {
        int id;
        int plays;

        Song(int id, int plays) {
            this.id = id;
            this.plays = plays;
        }
    }
}
