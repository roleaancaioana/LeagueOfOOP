package game;

public class Map {
    private static final Map map = new Map();
    private char[][] landTypes;

    private Map() {
    }

    public void buildMap(char[][] landTypes) {
        this.landTypes = landTypes;
    }

    public char[][] getLandTypes() {
        return landTypes;
    }

    public static Map getInstance() {
        if (map == null) {
            new Map();
        }
        return map;
    }
}
