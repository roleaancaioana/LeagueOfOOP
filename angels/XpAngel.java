package angels;

import game.Knight;
import game.Magician;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class XpAngel extends AngelVisitor {
    private Magician magician = Magician.getInstance();
    XpAngel(final String name, final String angelType, final int x, final int y) {
        super(name, angelType, x, y);
    }

    @Override
    public final void angelPower(final Pyromancer pyromancer) {
        final int xpModifier = 50;
        pyromancer.setXp(pyromancer.getXp() + xpModifier);
        pyromancer.levelUp();
    }

    @Override
    public final void angelPower(final Knight knight) {
        final int xpModifier = 45;
        knight.setXp(knight.getXp() + xpModifier);
        knight.levelUp();
    }

    @Override
    public final void angelPower(final Rogue rogue) {
        final int xpModifier = 40;
        rogue.setXp(rogue.getXp() + xpModifier);
        rogue.levelUp();
    }

    @Override
    public final void angelPower(final Wizard wizard) {
        final int xpModifier = 60;
        wizard.setXp(wizard.getXp() + xpModifier);
        wizard.levelUp();
    }

    @Override
    public final void notifyObserver() {
        magician.updateAngelPosition(this);
    }
}
