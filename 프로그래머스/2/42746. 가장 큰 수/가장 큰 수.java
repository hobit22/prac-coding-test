import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public String solution(int[] numbers) {
        return Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .sorted((s1, s2) -> {
                    int original = Integer.parseInt(s1 + s2);
                    int reverse = Integer.parseInt(s2 + s1);
                    return reverse - original;
                })
                .collect(Collectors.joining())
                .replaceAll("^0+", "0");
    }
}