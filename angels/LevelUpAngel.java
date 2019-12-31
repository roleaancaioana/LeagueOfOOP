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

        float newModifier = pyromancer.getModifier() + angelDamageModifier;
        pyromancer.setModifier(newModifier);
    }

    @Override
    public void angelPower(Knight knight) {
        this.addXpLevelUp(knight);
        final float angelDamageModifier = 0.1f;
        float newSlamModifier, newExecuteModifier;

        if (!knight.isSlamModifierisInitialZero()) {
            newSlamModifier = knight.getSlamModifier() + angelDamageModifier;
            knight.setSlamModifier(newSlamModifier);
        }
        if (!knight.isExecuteModifierIsInitialZero()) {
            newExecuteModifier = knight.getExecuteModifier() + angelDamageModifier;
            knight.setExecuteModifier(newExecuteModifier);
        }
    }

    @Override
    public void angelPower(Rogue rogue) {
        this.addXpLevelUp(rogue);
        final float angelDamageModifier = 0.15f;

        float newBackstabModifier = rogue.getBackstabModifier() + angelDamageModifier;
        float newParalysisModifier = rogue.getParalysisModifier() + angelDamageModifier;

        rogue.setBackstabModifier(newBackstabModifier);
        rogue.setParalysisModifier(newParalysisModifier);
    }

    @Override
    public void angelPower(Wizard wizard) {
        this.addXpLevelUp(wizard);
        final float angelDamageModifier = 0.25f;

        float newDrainModifier = wizard.getDrainModifier() + angelDamageModifier;
        float newDeflectModifier = wizard.getDeflectModifier() + angelDamageModifier;

        wizard.setDrainModifier(newDrainModifier);
        wizard.setDeflectModifier(newDeflectModifier);
    }
}
