// 1 = wall
// 2 = monster
// 3 = stone
// 4 = trap
// 5 = trap 2 phase
// 9 = door

public class Map {

    private int map[][][] = {
        {
            {1, 1, 1, 1, 1, 0, 0, 1}, 
            {1, 1, 0, 0, 2, 0, 0, 1}, 
            {1, 1, 0, 2, 0, 2, 1, 1}, 
            {1, 0, 0, 1, 1, 1, 1, 1}, 
            {1, 0, 3, 0, 0, 3, 0, 1}, 
            {1, 0, 3, 0, 3, 0, 0, 9} 
        },
        {   
            {1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 1, 1}, 
            {1, 2, 1, 4, 4, 0, 0}, 
            {0, 4, 1, 1, 3, 3, 3}, 
            {0, 0, 1, 1, 0, 4, 0}, 
            {0, 0, 1, 1, 0, 2, 0}, 
            {1, 1, 1, 1, 9, 0, 2}
        }
    };

    public Map() {}

    public int[][] getMap(int level) {
        int[][] copy = new int[map[level].length][map[level][0].length];
        for (int i = 0; i < map[level].length; i++) {
            for (int j = 0; j < map[level][i].length; j++) {
                copy[i][j] = map[level][i][j];
            } 
        }
        return copy;
    }

}
