package angels;

import game.Knight;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class Spawner extends AngelVisitor {
    Spawner(String angelType, int x, int y) {
        super(angelType, x, y);
    }

    @Override
    public void angelPower(Pyromancer pyromancer) {
        final boolean dead = false;
        final int newHp = 150;
        pyromancer.setDead(dead);
        pyromancer.setHp(newHp);
    }

    @Override
    public void angelPower(Knight knight) {
        final boolean dead = false;
        final int newHp = 200;
        knight.setDead(dead);
        knight.setHp(newHp);
    }

    @Override
    public void angelPower(Rogue rogue) {
        final boolean dead = false;
        final int newHp = 180;
        rogue.setDead(dead);
        rogue.setHp(newHp);
    }

    @Override
    public void angelPower(Wizard wizard) {
        final boolean dead = false;
        final int newHp = 120;
        wizard.setDead(dead);
        wizard.setHp(newHp);
    }
}
