package angels;

import game.Knight;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class DamageAngel extends AngelVisitor {
    DamageAngel(String name, String angelType, int x, int y) {
        super(name, angelType, x, y);
    }

    @Override
    public final void angelPower(Pyromancer pyromancer) {
        final float angelPyromancerModifier = 0.2f;
        pyromancer.changeAllModifiers(angelPyromancerModifier);
    }

    @Override
    public final void angelPower(Knight knight) {
        final float angelKnightModifier = 0.15f;
        knight.changeAllModifiers(angelKnightModifier);
    }

    @Override
    public final void angelPower(Rogue rogue) {
        final float angelRogueModifier = 0.3f;
        rogue.changeAllModifiers(angelRogueModifier);
    }

    @Override
    public final void angelPower(Wizard wizard) {
        final float angelWizardModifier = 0.4f;
        wizard.changeAllModifiers(angelWizardModifier);
    }
}
