package angels;

import game.Knight;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class SmallAngel extends AngelVisitor {
    SmallAngel(String name, String angelType, int x, int y) {
        super(name, angelType, x, y);
    }

    @Override
    public void angelPower(Pyromancer pyromancer) {
        final float angelDamageModifier = 0.15f;
        pyromancer.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 15;
        int newHp = pyromancer.getHp() + angelHpModifier;
        pyromancer.setHp(newHp);
    }

    @Override
    public void angelPower(Knight knight) {
        final float angelDamageModifier = 0.1f;
        knight.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 10;
        int newHp = knight.getHp() + angelHpModifier;
        knight.setHp(newHp);
    }

    @Override
    public void angelPower(Rogue rogue) {
        final float angelDamageModifier = 0.05f;
        rogue.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 20;
        int newHp = rogue.getHp() + angelHpModifier;
        rogue.setHp(newHp);
    }

    @Override
    public void angelPower(Wizard wizard) {
        final float angelDamageModifier = 0.1f;
        wizard.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 25;
        int newHp = wizard.getHp() + angelHpModifier;
        wizard.setHp(newHp);
    }
}
