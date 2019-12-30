package game;

<<<<<<< HEAD
public class RogueVisitor implements FighterVisitor {
=======
public class RogueVisitor implements Visitor {
>>>>>>> 6054d41c3db544ffcffa306e318828583d0ff416
    private float backstabModifier, paralysisModifier;

    @Override
    public final void attack(final Pyromancer pyromancer) {
        final float backstabPyromancerModifier = 1.25f;
        final float paralysisPyromancerModifier = 1.2f;
        backstabModifier = backstabPyromancerModifier;
        paralysisModifier = paralysisPyromancerModifier;
    }

    @Override
    public final void attack(final Knight knight) {
        final float backstabKnightModifier = 0.9f;
        final float paralysisKnightModifier = 0.8f;
        backstabModifier = backstabKnightModifier;
        paralysisModifier = paralysisKnightModifier;
    }

    @Override
    public final void attack(final Rogue rogue) {
        final float backstabRogueModifier = 1.2f;
        final float paralysisRogueModifier = 0.9f;
        backstabModifier = backstabRogueModifier;
        paralysisModifier = paralysisRogueModifier;
    }

    @Override
    public final void attack(final Wizard wizard) {
        final float backstabAndParalysisWizardModifier = 1.25f;
        backstabModifier = backstabAndParalysisWizardModifier;
        paralysisModifier = backstabAndParalysisWizardModifier;
    }

    final float getBackstabModifier() {
        return backstabModifier;
    }

    final float getParalysisModifier() {
        return paralysisModifier;
    }
}
