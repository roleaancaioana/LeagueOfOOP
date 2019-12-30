package game;

public class FirstKnightStrategy implements Strategy {
    @Override
    public int changeHp(int hp) {
        hp -= (int) hp/5;
        return hp;
    }

    @Override
    public float changeDamage(float damageCoefficient) {
        damageCoefficient *= 1.5f;
        return damageCoefficient;
    }
}
