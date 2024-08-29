import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        Map<String, List<Music>> musicListMap = new HashMap<>();
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            Music music = new Music(i, genres[i], plays[i]);

            String genre = music.genre;

            Integer count = map.getOrDefault(genre, 0);
            count += music.play;

            map.put(genre, count);

            List<Music> list = musicListMap.getOrDefault(genre, new ArrayList<>());
            list.add(music);
            musicListMap.put(genre, list);
        }

        List<String> keySet = new ArrayList<>(map.keySet());
        keySet.sort((genre1, genre2) -> map.get(genre2) - map.get(genre1));

        for (String genre : keySet) {
            List<Music> musicList = musicListMap.get(genre);
            musicList.sort((music1, music2) -> music2.play - music1.play);


            answer.add(musicList.get(0).index);
            if (musicList.size() > 1) {
                answer.add(musicList.get(1).index);
            }
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
}

class Music {
    int index;
    String genre;
    int play;
    
    public Music(int index, String genre, int play) {
        this.index = index;
        this.genre = genre;
        this.play = play;
    }
}