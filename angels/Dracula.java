package angels;

import game.Knight;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class Dracula implements AngelVisitor {
    @Override
    public final void angelPower(Pyromancer pyromancer) {
        final float angelDamageModifier = 0.7f;
        float newModifier = pyromancer.getModifier() * angelDamageModifier;
        pyromancer.setModifier(newModifier);

        final int angelHpModifier = 40;
        int newHp = pyromancer.getHp() - angelHpModifier;
        pyromancer.setHp(newHp);
    }

    @Override
    public final void angelPower(Knight knight) {
        final float angelDamageModifier = 0.8f;

        float newSlamModifier = knight.getSlamModifier() * angelDamageModifier;
        float newExecuteModifier = knight.getExecuteModifier() * angelDamageModifier;

        knight.setExecuteModifier(newExecuteModifier);
        knight.setSlamModifier(newSlamModifier);

        final int angelHpModifier = 60;
        int newHp = knight.getHp() - angelHpModifier;
        knight.setHp(newHp);
    }

    @Override
    public final void angelPower(Rogue rogue) {
        final float angelDamageModifier = 0.9f;

        float newBackstabModifier = rogue.getBackstabModifier() * angelDamageModifier;
        float newParalysisModifier = rogue.getParalysisModifier() * angelDamageModifier;

        rogue.setBackstabModifier(newBackstabModifier);
        rogue.setParalysisModifier(newParalysisModifier);

        final int angelHpModifier = 35;
        int newHp = rogue.getHp() - angelHpModifier;
        rogue.setHp(newHp);
    }

    @Override
    public final void angelPower(Wizard wizard) {
        final float angelDamageModifier = 0.6f;

        float newDrainModifier = wizard.getDrainModifier() * angelDamageModifier;
        float newDeflectModifier = wizard.getDeflectModifier() * angelDamageModifier;

        wizard.setDrainModifier(newDrainModifier);
        wizard.setDeflectModifier(newDeflectModifier);

        final int angelHpModifier = 20;
        int newHp = wizard.getHp() - angelHpModifier;
        wizard.setHp(newHp);
    }
}
