import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        List<Set<String>> orderList = Arrays.stream(orders)
            .map(String::chars)
            .map(charStream -> charStream
                    .mapToObj(menu -> String.valueOf((char) menu))
                    .collect(Collectors.toSet())
                )
            .collect(Collectors.toList());
        
        Map<Integer, List<Course>> courses = new HashMap<>();
        
        for (int length : course) {
            List<Course> list = new ArrayList<>();
            list.add(new Course("", 0));
            courses.put(length, list);
        }
        getCourses('A', new HashSet<>(), orderList, courses);
        
        return courses.values().stream()
                .filter(list -> list.get(0).occurences > 0)
                .flatMap(List::stream)
                .map(c -> c.course)
                .sorted()
                .toArray(String[]::new);
    }
    
    private static class Course {
        public final String course;
        public final int occurences;
        
        public Course(String course, int occurences) {
            this.course = course;
            this.occurences = occurences;
        }
        
        @Override
        public String toString() {
            return String.format("%s : %d", course, occurences);
        }
    }
    
    private void getCourses (
        char nextMenu, 
        Set<String> selectedMenus,
        List<Set<String>> orderList,
        Map<Integer, List<Course>> courses
    ) {
        // System.out.printf("nextMenu : %c, selectedMenus : %s\n", nextMenu, selectedMenus);
        // courses.forEach((key, value) -> {
        //     System.out.printf("course : %d, list : %s\n", key, value.toString());
        // });
        
        // 종료 조건
        int occurences = (int) orderList.stream()
            .filter(order -> order.containsAll(selectedMenus))
            .count();
   
        // 등장횟수가 2 미만
        if (occurences < 2) return;
        int size = selectedMenus.size();
        
        if (courses.containsKey(size)) {
            List<Course> courseList = courses.get(size);
            String str = selectedMenus.stream()
                .sorted()
                .collect(Collectors.joining(""));

            Course course = new Course(str, occurences);
            Course original = courseList.get(0);
            
            if (original.occurences < occurences) {
                courseList.clear();
                courseList.add(course);
            } else if (original.occurences == occurences) {
                courseList.add(course);
            }
        }
        
        
        for (char menuChar = nextMenu; menuChar <= 'Z'; menuChar++) {
            String menu = String.valueOf(menuChar);
            selectedMenus.add(menu);
            getCourses((char) (menuChar + 1), selectedMenus, orderList, courses);
            selectedMenus.remove(menu);
        }
    }
}