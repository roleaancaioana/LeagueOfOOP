package angels;

import game.Knight;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class XpAngel extends AngelVisitor {
    XpAngel(String name, String angelType, int x, int y) {
        super(name, angelType, x, y);
    }

    @Override
    public void angelPower(Pyromancer pyromancer) {
        final int xpModifier = 50;
        pyromancer.setXp(pyromancer.getXp() + xpModifier);
        pyromancer.levelUp();
    }

    @Override
    public void angelPower(Knight knight) {
        final int xpModifier = 45;
        knight.setXp(knight.getXp() + xpModifier);
        knight.levelUp();
    }

    @Override
    public void angelPower(Rogue rogue) {
        final int xpModifier = 40;
        rogue.setXp(rogue.getXp() + xpModifier);
        rogue.levelUp();
    }

    @Override
    public void angelPower(Wizard wizard) {
        final int xpModifier = 60;
        wizard.setXp(wizard.getXp() + xpModifier);
        wizard.levelUp();
    }
}
