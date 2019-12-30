package game;

public class FirstPyromancerStrategy implements Strategy {
    @Override
    public int changeHp(int hp) {
        hp -= (int) hp/4;
        return hp;
    }

    @Override
    public float changeDamage(float damageCoefficient) {
        damageCoefficient *= 1.7f;
        return damageCoefficient;
    }
}
