package angels;

import game.*;

public class LevelUpAngel implements AngelVisitor {
    private void addXpLevelUp(Hero hero) {
        final int xpLevelOne = 250;
        final int coefficient = 50;

        int xpLevelUp = xpLevelOne + hero.getLevel() * coefficient;
        hero.setXp(xpLevelUp);
        hero.setLevel(hero.getLevel() + 1);
    }

    @Override
    public void angelPower(Pyromancer pyromancer) {
        this.addXpLevelUp(pyromancer);
        final float angelDamageModifier = 1.2f;

        float newModifier = pyromancer.getModifier() * angelDamageModifier;
        pyromancer.setModifier(newModifier);
    }

    @Override
    public void angelPower(Knight knight) {
        this.addXpLevelUp(knight);
        final float angelDamageModifier = 1.1f;

        float newSlamModifier = knight.getSlamModifier() * angelDamageModifier;
        float newExecuteModifier = knight.getExecuteModifier() * angelDamageModifier;

        knight.setExecuteModifier(newExecuteModifier);
        knight.setSlamModifier(newSlamModifier);
    }

    @Override
    public void angelPower(Rogue rogue) {
        this.addXpLevelUp(rogue);
        final float angelDamageModifier = 1.15f;

        float newBackstabModifier = rogue.getBackstabModifier() * angelDamageModifier;
        float newParalysisModifier = rogue.getParalysisModifier() * angelDamageModifier;

        rogue.setBackstabModifier(newBackstabModifier);
        rogue.setParalysisModifier(newParalysisModifier);
    }

    @Override
    public void angelPower(Wizard wizard) {
        this.addXpLevelUp(wizard);
        final float angelDamageModifier = 1.25f;

        float newDrainModifier = wizard.getDrainModifier() * angelDamageModifier;
        float newDeflectModifier = wizard.getDeflectModifier() * angelDamageModifier;

        wizard.setDrainModifier(newDrainModifier);
        wizard.setDeflectModifier(newDeflectModifier);
    }
}
