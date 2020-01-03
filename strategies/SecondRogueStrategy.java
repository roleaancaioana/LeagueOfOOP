package strategies;

public class SecondRogueStrategy implements Strategy {
    @Override
    public final int changeHp(final int hp) {
        final int coefficient = 2;
        return (hp + hp / coefficient);
    }

    @Override
    public final float changeDamage(final float damageCoefficient) {
        final float coefficient = 0.1f;
        return (damageCoefficient - coefficient);
    }
}
