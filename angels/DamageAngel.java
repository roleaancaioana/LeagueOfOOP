package angels;

import game.Knight;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class DamageAngel implements AngelVisitor{

    @Override
    public final void angelPower(Pyromancer pyromancer) {
        final float angelPyromancerModifier = 1.2f;
        float newModifier = pyromancer.getModifier() * angelPyromancerModifier;
        pyromancer.setModifier(newModifier);
    }

    @Override
    public final void angelPower(Knight knight) {
        final float angelKnightModifier = 1.15f;

        float newSlamModifier = knight.getSlamModifier() * angelKnightModifier;
        float newExecuteModifier = knight.getExecuteModifier() * angelKnightModifier;

        knight.setExecuteModifier(newExecuteModifier);
        knight.setSlamModifier(newSlamModifier);
    }

    @Override
    public final void angelPower(Rogue rogue) {
        final float angelRogueModifier = 1.3f;

        float newBackstabModifier = rogue.getBackstabModifier() * angelRogueModifier;
        float newParalysisModifier = rogue.getParalysisModifier() * angelRogueModifier;

        rogue.setBackstabModifier(newBackstabModifier);
        rogue.setParalysisModifier(newParalysisModifier);
    }

    @Override
    public final void angelPower(Wizard wizard) {
        final float angelWizardModifier = 1.4f;

        float newDrainModifier = wizard.getDrainModifier() * angelWizardModifier;
        float newDeflectModifier = wizard.getDeflectModifier() * angelWizardModifier;

        wizard.setDrainModifier(newDrainModifier);
        wizard.setDeflectModifier(newDeflectModifier);
    }
}
