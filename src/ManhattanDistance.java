public class ManhattanDistance {

    public static int calculateManhattanDistance(int[][] puzzle, int[][] goal) {
        int distance = 0;
        int n = puzzle.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int currentNumber = puzzle[i][j];
                if (currentNumber != 0) { // 0 tahtada bir yere taşınacak
                    int[] goalPosition = findNumberPosition(goal, currentNumber, n);

                    int goalX = goalPosition[0];
                    int goalY = goalPosition[1];

                    distance += Math.abs(i - goalX) + Math.abs(j - goalY);
                }
            }
        }

        return distance;
    }

    private static int[] findNumberPosition(int[][] array, int number, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (array[i][j] == number) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}

