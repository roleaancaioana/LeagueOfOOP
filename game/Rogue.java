package game;

import angels.AngelVisitor;

public class Rogue extends Hero implements Fighter {
    private final int hpRogue = 600;
    private final int hpPerLevelRogue = 40;
    private int counterAttacksRogue;
    private final float backstabBaseDamage = 200;
    private final float backstabBaseDamagePerLevel = 20;
    private final float backstabWoodsModifier = 1.5f;
    private final float paralysisBaseDamage = 40;
    private final float paralysisBaseDamagePerLevel = 10;
    private final float bonusWoodsModifier = 1.15f;
    private int oldCounterAttacksRogue;
    private final int attacksCoefficient = 3;
    private float paralysisModifier, backstabModifier;

    Rogue(final char heroType, final int x, final int y, final char land) {
        super(heroType, x, y, land, "Rogue");
        super.setInitialHp(hpRogue);
        super.setHpPerLevel(hpPerLevelRogue);
        super.setHp(hpRogue);
        this.counterAttacksRogue = 0;
        this.oldCounterAttacksRogue = 0;
    }

    @Override
    public void executeStrategy() {
        Strategy strategy = super.getStrategy();
        int newHp = strategy.changeHp(super.getHp());
        super.setHp(newHp);
        this.paralysisModifier = strategy.changeDamage(this.paralysisModifier);
        this.backstabModifier = strategy.changeDamage(this.backstabModifier);
    }

    /**
     * Aceasta metoda ilustreaza modul de atac al unui erou de tip Rogue.
     * @param hero reprezinta oponentul eroului de tip Rogue.
     */
    @Override
    public final void attack(final Hero hero) {
        final int roundsOvertime = 3;
        final int roundsOvertimeWoods = 6;
        int passiveTurns;

        if (this.getLand() == 'W') {
            passiveTurns = roundsOvertimeWoods;
        } else {
            passiveTurns = roundsOvertime;
        }

        int intBackstabDamage = getBackstabDamage(hero);

        int intParalysisDamage = getParalysisDamage(hero);
        int totalActiveDamage = intBackstabDamage + intParalysisDamage;

        hero.setDamage(totalActiveDamage);
        hero.setDamageOvertime(intParalysisDamage);
        hero.setPassiveTurns(passiveTurns);
        hero.getActiveDamage();
        hero.becomeImmobilized(passiveTurns);
    }

    /**
     * Metoda ma va ajuta la calculul damage-ului rezultat in urma
     * aplicarii abilitatii Backstab a acestui erou.
     * @param hero reprezinta personajul cu care se va lupta acest erou
     * @return damage-ul rezultat in urma aplicarii abilitatii Backstab
     */
    private int getBackstabDamage(final Hero hero) {
        RogueVisitor visitor = new RogueVisitor();
        hero.accept(visitor);

        backstabModifier = visitor.getBackstabModifier();

        float backstabDamage = backstabBaseDamage + this.getLevel() * backstabBaseDamagePerLevel;

        if (this.counterAttacksRogue % attacksCoefficient == 0 && this.getLand() == 'W') {
            backstabDamage *= backstabWoodsModifier;
        }
        this.oldCounterAttacksRogue = this.counterAttacksRogue;
        this.counterAttacksRogue++;

        /*
         Aplic amplificatorul de rasa.
        */
        backstabDamage *= backstabModifier;

        /*
         Aplic amplificatorul corespunzator terenului Woods.
        */
        if (this.getLand() == 'W') {
            backstabDamage *= bonusWoodsModifier;
        }
        return (Math.round(backstabDamage));
    }

    /**
     * Metoda ma va ajuta la calculul damage-ului rezultat in urma
     * aplicarii abilitatii Paralysis a acestui erou.
     * @param hero reprezinta oponentul acestui erou
     * @return damage-ul rezultat in urma aplicarii abilitatii Paralysis
     */
    private int getParalysisDamage(final Hero hero) {
        RogueVisitor visitor = new RogueVisitor();
        hero.accept(visitor);

        paralysisModifier = visitor.getParalysisModifier();
        float paralysisDamage = paralysisBaseDamage + paralysisBaseDamagePerLevel * this.getLevel();

        /*
         Aplic amplificatorul de rasa.
        */
        paralysisDamage *= paralysisModifier;

        /*
         Aplic amplificatorul corespunzator terenului Woods.
        */
        if (this.getLand() == 'W') {
            paralysisDamage *= bonusWoodsModifier;
        }
        return (Math.round(paralysisDamage));
    }

    /**
     * Aceasta metoda ma va ajuta la calculul damage-ului total, fara amplificatorii
     * de rasa, pe care il da un erou de tip Rogue altui erou de tip Wizard.
     * @return damage-ul total dat, fara amplificatorii de rasa.
     */
    final int damageWithoutRaceModifiers() {
        float backstabDamageGived = backstabBaseDamage
                + this.getLevel() * backstabBaseDamagePerLevel;

        if (oldCounterAttacksRogue % attacksCoefficient == 0 && this.getLand() == 'W') {
            backstabDamageGived *= backstabWoodsModifier;
        }

        float paralysisDamageGived = paralysisBaseDamage
                + paralysisBaseDamagePerLevel * this.getLevel();

        if (this.getLand() == 'W') {
            backstabDamageGived *= bonusWoodsModifier;
            paralysisDamageGived *= bonusWoodsModifier;
        }

        int intBackstabDamageGived = Math.round(backstabDamageGived);
        int intParalysisDamageGived = Math.round(paralysisDamageGived);
        return (intBackstabDamageGived + intParalysisDamageGived);
    }

    @Override
    public final void accept(final FighterVisitor v) {
        v.attack(this);
    }

    public float getBackstabModifier() {
        return backstabModifier;
    }

    public void setBackstabModifier(float backstabModifier) {
        this.backstabModifier = backstabModifier;
    }

    public float getParalysisModifier() {
        return paralysisModifier;
    }

    public void setParalysisModifier(float paralysisModifier) {
        this.paralysisModifier = paralysisModifier;
    }

    @Override
    public void receiveAngelPower(AngelVisitor angelVisitor) {
        angelVisitor.angelPower(this);
    }
}
