package game;

public class KnightVisitor implements Visitor {
    private float executeModifier, slamModifier;
    @Override
    public final void attack(final Pyromancer pyromancer) {
        final float executePyromancerModifier = 1.1f;
        final float slamPyromancerModifier = 0.9f;
        executeModifier = executePyromancerModifier;
        slamModifier = slamPyromancerModifier;
    }

    @Override
    public final void attack(final Knight knight) {
        final float executeKnightModifier = 1.0f;
        final float slamKnightModifier = 1.2f;
        executeModifier = executeKnightModifier;
        slamModifier = slamKnightModifier;
    }

    @Override
    public final void attack(final Rogue rogue) {
        final float executeRogueModifier = 1.15f;
        final float slamRogueModifier = 0.8f;
        executeModifier = executeRogueModifier;
        slamModifier = slamRogueModifier;
    }

    @Override
    public final void attack(final Wizard wizard) {
        final float executeWizardModifier = 0.8f;
        final float slamWizardModifier = 1.05f;
        executeModifier = executeWizardModifier;
        slamModifier = slamWizardModifier;
    }

    final float getExecuteModifier() {
        return executeModifier;
    }
    final float getSlamModifier() {
        return slamModifier;
    }
}
