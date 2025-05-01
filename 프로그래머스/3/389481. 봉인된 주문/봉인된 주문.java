import java.util.*;

class Solution {

    long[] prefixSum = new long[12]; // 길이 i까지 누적 가능한 문자열 수
    List<Long> bannedOrders = new ArrayList<>();

    public String solution(long n, String[] bans) {
        precompute();

        // 삭제된 문자열의 사전 순서 인덱스 전처리
        for (String s : bans) {
            bannedOrders.add(getOrder(s));
        }
        Collections.sort(bannedOrders);

        // 이진 탐색 범위: 1 ~ 26^11 누적
        long left = 1;
        long right = prefixSum[11];
        long answerIndex = -1;

        while (left <= right) {
            long mid = (left + right) / 2;
            long bannedBefore = countBannedBefore(mid);
            long realOrder = mid - bannedBefore;

            if (realOrder >= n) {
                answerIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return indexToWord(answerIndex);
    }

    // 미리 길이별 누적 문자열 개수 계산
    void precompute() {
        long sum = 0;
        for (int i = 1; i <= 11; i++) {
            sum += pow26(i);
            prefixSum[i] = sum;
        }
    }

    // 문자열 s의 사전순 인덱스 (1-based)
    long getOrder(String s) {
        long order = 0;
        int len = s.length();
        for (int i = 1; i < len; i++) {
            order += pow26(i);
        }

        for (int i = 0; i < len; i++) {
            int ch = s.charAt(i) - 'a';
            order += ch * pow26(len - i - 1);
        }

        return order + 1;
    }

    // 사전순 인덱스를 문자열로 변환
    String indexToWord(long idx) {
        for (int len = 1; len <= 11; len++) {
            if (idx <= prefixSum[len]) {
                idx -= prefixSum[len - 1];
                return buildString(idx - 1, len); // 0-based
            }
        }
        return null;
    }

    // 문자열 만들기
    String buildString(long idx, int len) {
        char[] chars = new char[len];
        for (int i = len - 1; i >= 0; i--) {
            chars[i] = (char) ('a' + (idx % 26));
            idx /= 26;
        }
        return new String(chars);
    }

    // bannedOrders 중 idx 이하인 개수 (이진 탐색)
    long countBannedBefore(long idx) {
        int left = 0, right = bannedOrders.size() - 1;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (bannedOrders.get(mid) <= idx) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res + 1L;
    }

    // 26^exp (exp ≤ 11)
    long pow26(int exp) {
        long res = 1;
        for (int i = 0; i < exp; i++) {
            res *= 26;
        }
        return res;
    }
}
