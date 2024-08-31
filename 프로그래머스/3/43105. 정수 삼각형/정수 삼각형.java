class Solution {
    public int solution(int[][] triangle) {

        for (int i = triangle.length - 1; i >= 1; i--) {
            int[] currentRow = triangle[i];
            int[] nextRow = triangle[i - 1];

            for (int j = 0; j < currentRow.length - 1; j++) {
                int a = currentRow[j];
                int b = currentRow[j + 1];

                nextRow[j] += Math.max(a, b);
            }
        }

        return triangle[0][0];
    }
}