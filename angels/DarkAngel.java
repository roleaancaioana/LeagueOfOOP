package angels;

import game.Knight;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class DarkAngel extends AngelVisitor {
    DarkAngel(String name, String angelType, int x, int y) {
        super(name, angelType, x, y);
    }

    @Override
    public final void angelPower(Pyromancer pyromancer) {
        final int angelModifier = 30;
        int newHp = pyromancer.getHp() - angelModifier;
        pyromancer.setHp(newHp);
    }

    @Override
    public final void angelPower(Knight knight) {
        final int angelModifier = 40;
        int newHp = knight.getHp() - angelModifier;
        knight.setHp(newHp);
    }

    @Override
    public final void angelPower(Rogue rogue) {
        final int angelModifier = 10;
        int newHp = rogue.getHp() - angelModifier;
        rogue.setHp(newHp);
    }

    @Override
    public final void angelPower(Wizard wizard) {
        final int angelModifier = 20;
        int newHp = wizard.getHp() - angelModifier;
        wizard.setHp(newHp);
    }
}
