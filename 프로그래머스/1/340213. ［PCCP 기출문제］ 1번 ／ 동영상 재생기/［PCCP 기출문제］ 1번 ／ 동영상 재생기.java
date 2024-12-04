class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        int len = str2sec(video_len);
        int cur = str2sec(pos);
        int ops = str2sec(op_start);
        int ope = str2sec(op_end);
        
        for (String command : commands) {
            if (cur >= ops && cur <= ope) {
                cur = ope;
            }
            
            switch(command) {
                case "next" : {
                    if (cur + 10 > len) {
                        cur = len;
                        break;
                    } else {
                        cur += 10;
                    }
                    if (cur >= ops && cur <= ope) {
                        cur = ope;
                    }
                    break;
                }
                case "prev" : {
                    if (cur - 10 < 0) {
                        cur = 0;
                    } else {
                        cur -= 10;
                    }
                    
                    if (cur >= ops && cur <= ope) {
                        cur = ope;
                    }
                    break;
                }
            }
        }
        
        answer = sec2str(cur);
        
        return answer;
    }
    
    public int str2sec(String str) {
        String[] tokens = str.split(":");
        int min = Integer.parseInt(tokens[0]);
        int sec = Integer.parseInt(tokens[1]);
        return min * 60 + sec;
    }
    
    public String sec2str(int time) {
        int min = time / 60;
        int sec = time % 60;
        
        return String.format("%02d:%02d", min, sec);
    }
}