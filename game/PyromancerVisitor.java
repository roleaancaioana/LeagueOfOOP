package game;

<<<<<<< HEAD
public class PyromancerVisitor implements FighterVisitor {
=======
public class PyromancerVisitor implements Visitor {
>>>>>>> 6054d41c3db544ffcffa306e318828583d0ff416
    private float modifier;

    @Override
    public final void attack(final Pyromancer pyromancer) {
        final float pyromancerModifier = 0.9f;
        modifier = pyromancerModifier;
    }

    @Override
    public final void attack(final Knight knight) {
        final float knightModifier = 1.2f;
        modifier = knightModifier;
    }

    @Override
    public final void attack(final Rogue rogue) {
        final float rogueModifier = 0.8f;
        modifier = rogueModifier;
    }

    @Override
    public final void attack(final Wizard wizard) {
        final float wizardModifier = 1.05f;
        modifier = wizardModifier;
    }

    final float getModifier() {
        return modifier;
    }
}
