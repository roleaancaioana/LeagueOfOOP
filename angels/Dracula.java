package angels;

import game.Knight;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class Dracula extends AngelVisitor {
    Dracula(String name, String angelType, int x, int y) {
        super(name, angelType, x, y);
    }

    @Override
    public final void angelPower(Pyromancer pyromancer) {
        final float angelDamageModifier = 0.3f;
        float newModifier = pyromancer.getModifier() - angelDamageModifier;
        pyromancer.setModifier(newModifier);

        final int angelHpModifier = 40;
        int newHp = pyromancer.getHp() - angelHpModifier;
        pyromancer.setHp(newHp);
    }

    @Override
    public final void angelPower(Knight knight) {
        final float angelDamageModifier = 0.2f;
        float newSlamModifier, newExecuteModifier;

        if (!knight.isSlamModifierisInitialZero()) {
            newSlamModifier = knight.getSlamModifier() - angelDamageModifier;
            knight.setSlamModifier(newSlamModifier);
        }
        if (!knight.isExecuteModifierIsInitialZero()) {
            newExecuteModifier = knight.getExecuteModifier() - angelDamageModifier;
            knight.setExecuteModifier(newExecuteModifier);
        }

        final int angelHpModifier = 60;
        int newHp = knight.getHp() - angelHpModifier;
        knight.setHp(newHp);
    }

    @Override
    public final void angelPower(Rogue rogue) {
        final float angelDamageModifier = 0.1f;

        float newBackstabModifier = rogue.getBackstabModifier() - angelDamageModifier;
        float newParalysisModifier = rogue.getParalysisModifier() - angelDamageModifier;

        rogue.setBackstabModifier(newBackstabModifier);
        rogue.setParalysisModifier(newParalysisModifier);

        final int angelHpModifier = 35;
        int newHp = rogue.getHp() - angelHpModifier;
        rogue.setHp(newHp);
    }

    @Override
    public final void angelPower(Wizard wizard) {
        final float angelDamageModifier = 0.4f;

        float newDrainModifier = wizard.getDrainModifier() - angelDamageModifier;
        float newDeflectModifier = wizard.getDeflectModifier() - angelDamageModifier;

        wizard.setDrainModifier(newDrainModifier);
        wizard.setDeflectModifier(newDeflectModifier);

        final int angelHpModifier = 20;
        int newHp = wizard.getHp() - angelHpModifier;
        wizard.setHp(newHp);
    }
}
