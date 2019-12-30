package game;

public class Map {
    private static Map map;
    private char[][] landTypes;

    private Map() { }

    public void buildMap(char[][] landTypes) {
        this.landTypes = landTypes;
    }

    public char landType(int x, int y) {
        return landTypes[x][y];
    }

    public static Map getInstance() {
        if (map == null) {
            map = new Map();
        }
        return map;
    }
}
