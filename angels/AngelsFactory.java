package angels;

public final class AngelsFactory {
    public AngelVisitor getAngel(final String angelType, final int x, final int y) {
        if (angelType.equals("DamageAngel")) {
            return new DamageAngel(angelType, x, y);
        }
        if (angelType.equals("DarkAngel")) {
            return new DarkAngel(angelType, x, y);
        }
        if (angelType.equals("Dracula")) {
            return new Dracula(angelType, x, y);
        }
        if (angelType.equals("GoodBoy")) {
            return new GoodBoy(angelType, x, y);
        }
        if (angelType.equals("LevelUpAngel")) {
            return new LevelUpAngel(angelType, x, y);
        }
        if (angelType.equals("LifeGiver")) {
            return new LifeGiver(angelType, x, y);
        }
        if (angelType.equals("SmallAngel")) {
            return new SmallAngel(angelType, x, y);
        }
        if (angelType.equals("Spawner")) {
            return new Spawner(angelType, x, y);
        }
        if (angelType.equals("TheDoomer")) {
            return new TheDoomer(angelType, x, y);
        }
        if (angelType.equals("XpAngel")) {
            return new XpAngel(angelType, x, y);
        }
        return null;
    }
}
