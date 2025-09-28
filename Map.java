// 1 = wall
// 2 = monster
// 3 = stone
// 4 = trap
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
        }
    };

    public Map() {}

    public int[][] getMap(int level) {
        return map[level];
    }

}
