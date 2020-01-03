package angels;

/**
 * Aceasta clasa ajuta la crearea ingerilor.
 */
public final class AngelsFactory {
    public AngelVisitor getAngel(final String name, final int x, final int y) {
        if (name.equals("DamageAngel")) {
            return new DamageAngel(name, "good", x, y);
        }
        if (name.equals("DarkAngel")) {
            return new DarkAngel(name, "bad", x, y);
        }
        if (name.equals("Dracula")) {
            return new Dracula(name, "bad", x, y);
        }
        if (name.equals("GoodBoy")) {
            return new GoodBoy(name, "good", x, y);
        }
        if (name.equals("LevelUpAngel")) {
            return new LevelUpAngel(name, "good", x, y);
        }
        if (name.equals("LifeGiver")) {
            return new LifeGiver(name, "good", x, y);
        }
        if (name.equals("SmallAngel")) {
            return new SmallAngel(name, "good", x, y);
        }
        if (name.equals("Spawner")) {
            return new Spawner(name, "good", x, y);
        }
        if (name.equals("TheDoomer")) {
            return new TheDoomer(name, "bad", x, y);
        }
        if (name.equals("XPAngel")) {
            return new XpAngel(name, "good", x, y);
        }
        return null;
    }
}
