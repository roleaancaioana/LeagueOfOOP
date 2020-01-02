package angels;

import game.Knight;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class GoodBoy extends AngelVisitor {
    GoodBoy(String name, String angelType, int x, int y) {
        super(name, angelType, x, y);
    }

    @Override
    public final void angelPower(Pyromancer pyromancer) {
        final float angelDamageModifier = 0.5f;
        pyromancer.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 30;
        int newHp = pyromancer.getHp() + angelHpModifier;
        pyromancer.setHp(newHp);
    }

    @Override
    public final void angelPower(Knight knight) {
        final float angelDamageModifier = 0.4f;
        knight.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 20;
        int newHp = knight.getHp() + angelHpModifier;
        knight.setHp(newHp);
    }

    @Override
    public final void angelPower(Rogue rogue) {
        final float angelDamageModifier = 0.4f;
        rogue.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 40;
        int newHp = rogue.getHp() + angelHpModifier;
        rogue.setHp(newHp);
    }

    @Override
    public final void angelPower(Wizard wizard) {
        final float angelDamageModifier = 0.3f;
        wizard.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 50;
        int newHp = wizard.getHp() + angelHpModifier;
        wizard.setHp(newHp);
    }
}
