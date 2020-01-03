package angels;

import game.Knight;
import game.Magician;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class SmallAngel extends AngelVisitor {
    private Magician magician = Magician.getInstance();
    SmallAngel(final String name, final String angelType, final int x, final int y) {
        super(name, angelType, x, y);
    }

    @Override
    public final void angelPower(final Pyromancer pyromancer) {
        final float angelDamageModifier = 0.15f;
        pyromancer.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 15;
        int newHp = pyromancer.getHp() + angelHpModifier;
        pyromancer.setHp(newHp);
    }

    @Override
    public final void angelPower(final Knight knight) {
        final float angelDamageModifier = 0.1f;
        knight.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 10;
        int newHp = knight.getHp() + angelHpModifier;
        knight.setHp(newHp);
    }

    @Override
    public final void angelPower(final Rogue rogue) {
        final float angelDamageModifier = 0.05f;
        rogue.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 20;
        int newHp = rogue.getHp() + angelHpModifier;
        rogue.setHp(newHp);
    }

    @Override
    public final void angelPower(final Wizard wizard) {
        final float angelDamageModifier = 0.1f;
        wizard.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 25;
        int newHp = wizard.getHp() + angelHpModifier;
        wizard.setHp(newHp);
    }

    @Override
    public final void notifyObserver() {
        magician.updateAngelPosition(this);
    }
}
