package life;

public class Generate {

    public static char[][] generate(char[][] map) {
        char[][] nextMap = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int count = checkNeighbors(map, i, j);
                if (checkAlive(map[i][j], count)) {
                    nextMap[i][j] = 'O';
                } else {
                    nextMap[i][j] = ' ';
                }
            }
        }
        return nextMap;
    }

    public static int checkNeighbors(char[][] map, int row, int column) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i == row && j == column) {
                    continue;
                }
                if (map[i < 0 ? map.length - 1 : i == map.length ? 0 : i]
                        [j < 0 ? map[0].length - 1 : j == map[0].length ? 0 : j] == 'O') {
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean checkAlive(char ch, int count) {
        if (ch == 'O') {
            return count >= 2 && count <= 3;
        } else {
            return count == 3;
        }
    }
}
