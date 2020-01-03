package angels;

import game.Knight;
import game.Magician;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class Spawner extends AngelVisitor {
    private Magician magician = Magician.getInstance();
    Spawner(final String name, final String angelType, final int x, final int y) {
        super(name, angelType, x, y);
    }

    @Override
    public final void angelPower(final Pyromancer pyromancer) {
        if (pyromancer.isDead()) {
            final boolean dead = false;
            final int newHp = 150;
            pyromancer.setDead(dead);
            pyromancer.setHp(newHp);
        }
    }

    @Override
    public final void angelPower(final Knight knight) {
        if (knight.isDead()) {
            final boolean dead = false;
            final int newHp = 200;
            knight.setDead(dead);
            knight.setHp(newHp);
        }
    }

    @Override
    public final void angelPower(final Rogue rogue) {
        if (rogue.isDead()) {
            final boolean dead = false;
            final int newHp = 180;
            rogue.setDead(dead);
            rogue.setHp(newHp);
        }
    }

    @Override
    public final void angelPower(final Wizard wizard) {
        if (wizard.isDead()) {
            final boolean dead = false;
            final int newHp = 120;
            wizard.setDead(dead);
            wizard.setHp(newHp);
        }
    }

    @Override
    public final void notifyObserver() {
        magician.updateAngelPosition(this);
    }
}
