package angels;

import game.*;

public class LevelUpAngel extends AngelVisitor {
    LevelUpAngel(String name, String angelType, int x, int y) {
        super(name, angelType, x, y);
    }

    private void addXpLevelUp(Hero hero) {
        final int xpLevelOne = 250;
        final int coefficient = 50;

        int xpLevelUp = xpLevelOne + hero.getLevel() * coefficient;
        hero.setXp(xpLevelUp);
        hero.setLevel(hero.getLevel() + 1);
        hero.setHpLevelUp();
    }

    @Override
    public void angelPower(Pyromancer pyromancer) {
        this.addXpLevelUp(pyromancer);
        final float angelDamageModifier = 0.2f;
        pyromancer.changeAllModifiers(angelDamageModifier);
    }

    @Override
    public void angelPower(Knight knight) {
        this.addXpLevelUp(knight);
        final float angelDamageModifier = 0.1f;
        knight.changeAllModifiers(angelDamageModifier);
    }

    @Override
    public void angelPower(Rogue rogue) {
        this.addXpLevelUp(rogue);
        final float angelDamageModifier = 0.15f;
        rogue.changeAllModifiers(angelDamageModifier);
    }

    @Override
    public void angelPower(Wizard wizard) {
        this.addXpLevelUp(wizard);
        final float angelDamageModifier = 0.25f;
        wizard.changeAllModifiers(angelDamageModifier);
    }
}
