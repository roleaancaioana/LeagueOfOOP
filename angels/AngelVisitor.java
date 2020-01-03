package angels;

import game.Knight;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;
import game.Magician;

/**
 * Aceasta clasa ilustreaza toate caracteristicile comune ale ingerilor.
 */
public abstract class AngelVisitor {
    private String name, angelType;
    private int x, y;
    private Magician magician = Magician.getInstance();

    AngelVisitor(final String name, final String angelType, final int x, final int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.angelType = angelType;
    }

    public final String getAngelType() {
        return angelType;
    }

    public final void setAngelType(final String angelType) {
        this.angelType = angelType;
    }

    public final int getY() {
        return y;
    }

    public final void setY(final int y) {
        this.y = y;
    }

    public final int getX() {
        return x;
    }

    public final void setX(final int x) {
        this.x = x;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public abstract void angelPower(Pyromancer pyromancer);
    public abstract void angelPower(Knight knight);
    public abstract void angelPower(Rogue rogue);
    public abstract void angelPower(Wizard wizard);
    public abstract void notifyObserver();
}
