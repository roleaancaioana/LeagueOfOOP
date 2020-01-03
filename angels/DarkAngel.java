package angels;

import game.Knight;
import game.Magician;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class DarkAngel extends AngelVisitor {
    private Magician magician = Magician.getInstance();
    DarkAngel(final String name, final String angelType, final int x, final int y) {
        super(name, angelType, x, y);
    }

    @Override
    public final void angelPower(final Pyromancer pyromancer) {
        final int angelModifier = 30;
        int newHp = pyromancer.getHp() - angelModifier;
        pyromancer.setHp(newHp);
    }

    @Override
    public final void angelPower(final Knight knight) {
        final int angelModifier = 40;
        int newHp = knight.getHp() - angelModifier;
        knight.setHp(newHp);
    }

    @Override
    public final void angelPower(final Rogue rogue) {
        final int angelModifier = 10;
        int newHp = rogue.getHp() - angelModifier;
        rogue.setHp(newHp);
    }

    @Override
    public final void angelPower(final Wizard wizard) {
        final int angelModifier = 20;
        int newHp = wizard.getHp() - angelModifier;
        wizard.setHp(newHp);
    }

    @Override
    public final void notifyObserver() {
        magician.updateAngelPosition(this);
    }
}
