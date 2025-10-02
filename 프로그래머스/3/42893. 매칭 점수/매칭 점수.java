import java.util.*;
import java.util.regex.*;

class Solution {
    static class Page {
        String url;
        int basicScore;
        int externalLinks;
        double linkScore;
        List<String> outLinks;
        
        Page(String url) {
            this.url = url;
            this.outLinks = new ArrayList<>();
            this.linkScore = 0.0;
        }
    }
    
    public int solution(String word, String[] pages) {
        List<Page> pageList = new ArrayList<>();
        Map<String, Integer> urlToIndex = new HashMap<>();
        
        // 1. 각 페이지 정보 파싱
        for (int i = 0; i < pages.length; i++) {
            String html = pages[i];
            
            // URL 추출
            String url = extractUrl(html);
            Page page = new Page(url);
            urlToIndex.put(url, i);
            
            // 기본 점수 계산
            page.basicScore = countWord(word, html);
            
            // 외부 링크 추출
            page.outLinks = extractLinks(html);
            page.externalLinks = page.outLinks.size();
            
            pageList.add(page);
        }
        
        // 2. 링크 점수 계산
        for (Page page : pageList) {
            if (page.externalLinks > 0) {
                double scorePerLink = (double) page.basicScore / page.externalLinks;
                
                // 이 페이지가 링크하는 다른 페이지들에게 점수 분배
                for (String targetUrl : page.outLinks) {
                    if (urlToIndex.containsKey(targetUrl)) {
                        int targetIdx = urlToIndex.get(targetUrl);
                        pageList.get(targetIdx).linkScore += scorePerLink;
                    }
                }
            }
        }
        
        // 3. 매칭 점수 계산 및 최댓값 찾기
        int answer = 0;
        double maxScore = 0.0;
        
        for (int i = 0; i < pageList.size(); i++) {
            Page page = pageList.get(i);
            double matchingScore = page.basicScore + page.linkScore;
            
            if (matchingScore > maxScore) {
                maxScore = matchingScore;
                answer = i;
            }
        }
        
        return answer;
    }
    
    private String extractUrl(String html) {
        Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"(https://[^\"]+)\"");
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
    
    private int countWord(String word, String html) {
        int count = 0;
        String lowerWord = word.toLowerCase();
        String lowerHtml = html.toLowerCase();
        
        Pattern pattern = Pattern.compile("[a-z]+");
        Matcher matcher = pattern.matcher(lowerHtml);
        
        while (matcher.find()) {
            if (matcher.group().equals(lowerWord)) {
                count++;
            }
        }
        
        return count;
    }
    
    private List<String> extractLinks(String html) {
        List<String> links = new ArrayList<>();
        Pattern pattern = Pattern.compile("<a href=\"(https://[^\"]+)\"");
        Matcher matcher = pattern.matcher(html);
        
        while (matcher.find()) {
            links.add(matcher.group(1));
        }
        
        return links;
    }
}