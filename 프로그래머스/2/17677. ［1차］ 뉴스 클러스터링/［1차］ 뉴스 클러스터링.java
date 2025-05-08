import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        List<String> multiset1 = getBigrams(str1);
        List<String> multiset2 = getBigrams(str2);

        // 다중집합 원소별 개수 카운팅
        Map<String, Integer> map1 = getCountMap(multiset1);
        Map<String, Integer> map2 = getCountMap(multiset2);

        int intersection = 0;
        int union = 0;

        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (String key : allKeys) {
            int count1 = map1.getOrDefault(key, 0);
            int count2 = map2.getOrDefault(key, 0);
            intersection += Math.min(count1, count2);
            union += Math.max(count1, count2);
        }

        // 유사도 계산
        if (union == 0) return 65536;

        double jaccard = (double) intersection / union;
        return (int)(jaccard * 65536);
    }

    // 문자열을 2글자씩 끊어서 유효한 bigram 리스트 생성
    private List<String> getBigrams(String str) {
        List<String> bigrams = new ArrayList<>();
        str = str.toLowerCase();

        for (int i = 0; i < str.length() - 1; i++) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + 1);
            if (Character.isLetter(c1) && Character.isLetter(c2)) {
                bigrams.add(str.substring(i, i + 2));
            }
        }

        return bigrams;
    }

    // 다중집합의 요소별 개수 카운트 맵 생성
    private Map<String, Integer> getCountMap(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : list) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        return map;
    }
}