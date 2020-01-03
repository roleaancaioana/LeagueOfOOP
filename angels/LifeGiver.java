package angels;

import game.Knight;
import game.Magician;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class LifeGiver extends AngelVisitor {
    private Magician magician = Magician.getInstance();
    LifeGiver(final String name, final String angelType, final int x, final int y) {
        super(name, angelType, x, y);
    }

    @Override
    public final void angelPower(final Pyromancer pyromancer) {
        final int angelModifier = 80;
        final int maxLevelHp = pyromancer.getLevel() * pyromancer.getHpPerLevel()
                + pyromancer.getInitialHp();
        int newHp = pyromancer.getHp() + angelModifier;
        if (newHp > maxLevelHp) {
            newHp = maxLevelHp;
        }
        pyromancer.setHp(newHp);
    }

    @Override
    public final void angelPower(final Knight knight) {
        final int angelModifier = 100;
        final int maxLevelHp = knight.getLevel() * knight.getHpPerLevel() + knight.getInitialHp();
        int newHp = knight.getHp() + angelModifier;
        if (newHp > maxLevelHp) {
            newHp = maxLevelHp;
        }
        knight.setHp(newHp);
    }

    @Override
    public final void angelPower(final Rogue rogue) {
        final int angelModifier = 90;
        final int maxLevelHp = rogue.getLevel() * rogue.getHpPerLevel() + rogue.getInitialHp();
        int newHp = rogue.getHp() + angelModifier;
        if (newHp > maxLevelHp) {
            newHp = maxLevelHp;
        }
        rogue.setHp(newHp);
    }

    @Override
    public final void angelPower(final Wizard wizard) {
        final int angelModifier = 120;
        final int maxLevelHp = wizard.getLevel() * wizard.getHpPerLevel() + wizard.getInitialHp();
        int newHp = wizard.getHp() + angelModifier;
        if (newHp > maxLevelHp) {
            newHp = maxLevelHp;
        }
        wizard.setHp(newHp);
    }

    @Override
    public final void notifyObserver() {
        magician.updateAngelPosition(this);
    }
}
