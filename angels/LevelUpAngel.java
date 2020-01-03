package angels;

import game.Knight;
import game.Magician;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;
import game.Hero;

public class LevelUpAngel extends AngelVisitor {
    private Magician magician = Magician.getInstance();
    LevelUpAngel(final String name, final String angelType, final int x, final int y) {
        super(name, angelType, x, y);
    }

    private void addXpLevelUp(final Hero hero) {
        final int xpLevelOne = 250;
        final int coefficient = 50;

        int xpLevelUp = xpLevelOne + hero.getLevel() * coefficient;
        hero.setXp(xpLevelUp);
        hero.setLevel(hero.getLevel() + 1);
        hero.setHpLevelUp();
    }

    @Override
    public final void angelPower(final Pyromancer pyromancer) {
        this.addXpLevelUp(pyromancer);
        final float angelDamageModifier = 0.2f;
        pyromancer.changeAllModifiers(angelDamageModifier);
    }

    @Override
    public final void angelPower(final Knight knight) {
        this.addXpLevelUp(knight);
        final float angelDamageModifier = 0.1f;
        knight.changeAllModifiers(angelDamageModifier);
    }

    @Override
    public final void angelPower(final Rogue rogue) {
        this.addXpLevelUp(rogue);
        final float angelDamageModifier = 0.15f;
        rogue.changeAllModifiers(angelDamageModifier);
    }

    @Override
    public final void angelPower(final Wizard wizard) {
        this.addXpLevelUp(wizard);
        final float angelDamageModifier = 0.25f;
        wizard.changeAllModifiers(angelDamageModifier);
    }

    @Override
    public final void notifyObserver() {
        magician.updateAngelPosition(this);
    }
}
