package strategies;

public class FirstRogueStrategy implements Strategy {
    @Override
    public final int changeHp(final int hp) {
        final int coefficient = 7;
        return (hp - hp / coefficient);
    }

    @Override
    public final float changeDamage(final float damageCoefficient) {
        final float coefficient = 0.4f;
        return (damageCoefficient + coefficient);
    }
}
