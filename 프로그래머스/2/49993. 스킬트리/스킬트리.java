class Solution {
    public int solution(String skill, String[] skill_trees) {
        int count = 0;

        for (String tree : skill_trees) {
            StringBuilder filtered = new StringBuilder();
            
            for (char c : tree.toCharArray()) {
                if (skill.indexOf(c) != -1) {
                    filtered.append(c);
                }
            }

            if (skill.startsWith(filtered.toString())) {
                count++;
            }
        }

        return count;
    }
}