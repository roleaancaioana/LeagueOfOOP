package angels;

import game.Knight;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class LifeGiver implements AngelVisitor {
    @Override
    public void angelPower(Pyromancer pyromancer) {
        final int angelModifier = 80;
        int newHp = pyromancer.getHp() + angelModifier;
        pyromancer.setHp(newHp);
    }

    @Override
    public void angelPower(Knight knight) {
        final int angelModifier = 100;
        int newHp = knight.getHp() + angelModifier;
        knight.setHp(newHp);
    }

    @Override
    public void angelPower(Rogue rogue) {
        final int angelModifier = 90;
        int newHp = rogue.getHp() + angelModifier;
        rogue.setHp(newHp);
    }

    @Override
    public void angelPower(Wizard wizard) {
        final int angelModifier = 120;
        int newHp = wizard.getHp() + angelModifier;
        wizard.setHp(newHp);
    }
}
