package game;

public class FirstRogueStrategy implements Strategy {
    @Override
    public int changeHp(int hp) {
        hp -= (int) hp/7;
        return hp;
    }

    @Override
    public float changeDamage(float damageCoefficient) {
        damageCoefficient += 0.4f;
        return damageCoefficient;
    }
}
