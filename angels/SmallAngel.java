package angels;

import game.Knight;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class SmallAngel implements AngelVisitor {
    @Override
    public void angelPower(Pyromancer pyromancer) {
        final float angelDamageModifier = 1.15f;
        float newModifier = pyromancer.getModifier() * angelDamageModifier;
        pyromancer.setModifier(newModifier);

        final int angelHpModifier = 15;
        int newHp = pyromancer.getHp() + angelHpModifier;
        pyromancer.setHp(newHp);
    }

    @Override
    public void angelPower(Knight knight) {
        final float angelDamageModifier = 1.1f;

        float newSlamModifier = knight.getSlamModifier() * angelDamageModifier;
        float newExecuteModifier = knight.getExecuteModifier() * angelDamageModifier;

        knight.setExecuteModifier(newExecuteModifier);
        knight.setSlamModifier(newSlamModifier);

        final int angelHpModifier = 10;
        int newHp = knight.getHp() + angelHpModifier;
        knight.setHp(newHp);
    }

    @Override
    public void angelPower(Rogue rogue) {
        final float angelDamageModifier = 1.05f;

        float newBackstabModifier = rogue.getBackstabModifier() * angelDamageModifier;
        float newParalysisModifier = rogue.getParalysisModifier() * angelDamageModifier;

        rogue.setBackstabModifier(newBackstabModifier);
        rogue.setParalysisModifier(newParalysisModifier);

        final int angelHpModifier = 20;
        int newHp = rogue.getHp() + angelHpModifier;
        rogue.setHp(newHp);
    }

    @Override
    public void angelPower(Wizard wizard) {
        final float angelDamageModifier = 1.1f;

        float newDrainModifier = wizard.getDrainModifier() * angelDamageModifier;
        float newDeflectModifier = wizard.getDeflectModifier() * angelDamageModifier;

        wizard.setDrainModifier(newDrainModifier);
        wizard.setDeflectModifier(newDeflectModifier);

        final int angelHpModifier = 25;
        int newHp = wizard.getHp() + angelHpModifier;
        wizard.setHp(newHp);
    }
}
