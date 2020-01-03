package angels;

import game.Knight;
import game.Magician;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class DamageAngel extends AngelVisitor {
    private Magician magician = Magician.getInstance();
    DamageAngel(final String name, final String angelType, final int x, final int y) {
        super(name, angelType, x, y);
    }

    @Override
    public final void angelPower(final Pyromancer pyromancer) {
        final float angelPyromancerModifier = 0.2f;
        pyromancer.changeAllModifiers(angelPyromancerModifier);
    }

    @Override
    public final void angelPower(final Knight knight) {
        final float angelKnightModifier = 0.15f;
        knight.changeAllModifiers(angelKnightModifier);
    }

    @Override
    public final void angelPower(final Rogue rogue) {
        final float angelRogueModifier = 0.3f;
        rogue.changeAllModifiers(angelRogueModifier);
    }

    @Override
    public final void angelPower(final Wizard wizard) {
        final float angelWizardModifier = 0.4f;
        wizard.changeAllModifiers(angelWizardModifier);
    }

    @Override
    public final void notifyObserver() {
        magician.updateAngelPosition(this);
    }
}
