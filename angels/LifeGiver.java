package angels;

import game.*;

public class LifeGiver extends AngelVisitor {
    LifeGiver(String name, String angelType, int x, int y) {
        super(name, angelType, x, y);
    }

    @Override
    public void angelPower(Pyromancer pyromancer) {
        final int angelModifier = 80;
        final int maxLevelHp = pyromancer.getLevel() * pyromancer.getHpPerLevel() + pyromancer.getInitialHp();
        int newHp = pyromancer.getHp() + angelModifier;
        if (newHp > maxLevelHp) {
            newHp = maxLevelHp;
        }
        pyromancer.setHp(newHp);
    }

    @Override
    public void angelPower(Knight knight) {
        final int angelModifier = 100;
        final int maxLevelHp = knight.getLevel() * knight.getHpPerLevel() + knight.getInitialHp();
        int newHp = knight.getHp() + angelModifier;
        if (newHp > maxLevelHp) {
            newHp = maxLevelHp;
        }
        knight.setHp(newHp);
    }

    @Override
    public void angelPower(Rogue rogue) {
        final int angelModifier = 90;
        final int maxLevelHp = rogue.getLevel() * rogue.getHpPerLevel() + rogue.getInitialHp();
        int newHp = rogue.getHp() + angelModifier;
        if (newHp > maxLevelHp) {
            newHp = maxLevelHp;
        }
        rogue.setHp(newHp);
    }

    @Override
    public void angelPower(Wizard wizard) {
        final int angelModifier = 120;
        final int maxLevelHp = wizard.getLevel() * wizard.getHpPerLevel() + wizard.getInitialHp();
        int newHp = wizard.getHp() + angelModifier;
        if (newHp > maxLevelHp) {
            newHp = maxLevelHp;
        }
        wizard.setHp(newHp);
    }
}
