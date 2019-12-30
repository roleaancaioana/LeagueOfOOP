package angels;

import game.Knight;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class TheDoomer extends AngelVisitor {
    TheDoomer(String angelType, int x, int y) {
        super(angelType, x, y);
    }

    @Override
    public void angelPower(Pyromancer pyromancer) {
        final boolean dead = true;
        pyromancer.setDead(dead);
    }

    @Override
    public void angelPower(Knight knight) {
        final boolean dead = true;
        knight.setDead(dead);
    }

    @Override
    public void angelPower(Rogue rogue) {
        final boolean dead = true;
        rogue.setDead(dead);
    }

    @Override
    public void angelPower(Wizard wizard) {
        final boolean dead = true;
        wizard.setDead(dead);
    }
}
