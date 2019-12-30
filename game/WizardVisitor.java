package game;

<<<<<<< HEAD
public class WizardVisitor implements FighterVisitor {
=======
public class WizardVisitor implements Visitor {
>>>>>>> 6054d41c3db544ffcffa306e318828583d0ff416
    private float drainModifier, deflectModifier;

    @Override
    public final void attack(final Pyromancer pyromancer) {
        final float drainPyromancerModifier = 0.9f;
        final float deflectPyromancerModifier = 1.3f;
        drainModifier = drainPyromancerModifier;
        deflectModifier = deflectPyromancerModifier;
    }

    @Override
    public final void attack(final Knight knight) {
        final float drainKnightModifier = 1.2f;
        final float deflectKnightModifier = 1.4f;
        drainModifier = drainKnightModifier;
        deflectModifier = deflectKnightModifier;
    }

    @Override
    public final void attack(final Rogue rogue) {
        final float drainRogueModifier = 0.8f;
        final float deflectRogueModifier = 1.2f;
        drainModifier = drainRogueModifier;
        deflectModifier = deflectRogueModifier;
    }

    @Override
    public final void attack(final Wizard wizard) {
        final float drainWizardModifier = 1.05f;
        drainModifier = drainWizardModifier;
    }
    final float getDrainModifier() {
        return drainModifier;
    }
    final float getDeflectModifier() {
        return deflectModifier;
    }
}
