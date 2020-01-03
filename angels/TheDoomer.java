package angels;

import game.Knight;
import game.Magician;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class TheDoomer extends AngelVisitor {
    private Magician magician = Magician.getInstance();
    TheDoomer(final String name, final String angelType, final int x, final int y) {
        super(name, angelType, x, y);
    }

    @Override
    public final void angelPower(final Pyromancer pyromancer) {
        final boolean dead = true;
        pyromancer.setDead(dead);
    }

    @Override
    public final void angelPower(final Knight knight) {
        final boolean dead = true;
        knight.setDead(dead);
    }

    @Override
    public final void angelPower(final Rogue rogue) {
        final boolean dead = true;
        rogue.setDead(dead);
    }

    @Override
    public final void angelPower(final Wizard wizard) {
        final boolean dead = true;
        wizard.setDead(dead);
    }

    @Override
    public final void notifyObserver() {
        magician.updateAngelPosition(this);
    }
}
