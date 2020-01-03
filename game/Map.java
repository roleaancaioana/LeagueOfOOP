package game;

/**
 * Aceasta clasa ma va ajuta sa retin tipurile de teren din joc.
 */
public final class Map {
    private static Map map;
    private char[][] landTypes;

    private Map() { }

    void buildMap(final char[][] mapLandTypes) {
        this.landTypes = mapLandTypes;
    }

    char landType(final int x, final int y) {
        return landTypes[x][y];
    }

    public static Map getInstance() {
        if (map == null) {
            map = new Map();
        }
        return map;
    }
}
