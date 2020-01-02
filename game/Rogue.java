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
    private float backstabKnightModifier, paralysisKnightModifier;
    private float backstabPyromancerModifier, paralysisPyromancerModifier;
    private float backstabRogueModifier, paralysisRogueModifier;
    private float backstabWizardModifier, paralysisWizardModifier;

    Rogue(final char heroType, final int x, final int y, final char land) {
        super(heroType, x, y, land, "Rogue");
        super.setInitialHp(hpRogue);
        super.setHpPerLevel(hpPerLevelRogue);
        super.setHp(hpRogue);
        this.counterAttacksRogue = 0;
        this.oldCounterAttacksRogue = 0;
        this.backstabModifier = 0;
        this.paralysisModifier = 0;
        this.backstabRogueModifier = 1.2f;
        this.paralysisRogueModifier = 0.9f;
        this.backstabWizardModifier = 1.25f;
        this.paralysisWizardModifier = 1.25f;
        this.backstabKnightModifier = 0.9f;
        this.paralysisKnightModifier = 0.8f;
        this.backstabPyromancerModifier = 1.25f;
        this.paralysisPyromancerModifier = 1.2f;
    }

    @Override
    public void executeStrategy() {
        Strategy strategy = super.getStrategy();
        int newHp = strategy.changeHp(super.getHp());
        super.setHp(newHp);
        this.backstabRogueModifier = strategy.changeDamage(this.backstabRogueModifier);
        this.paralysisRogueModifier = strategy.changeDamage(this.paralysisRogueModifier);
        this.backstabWizardModifier = strategy.changeDamage(this.backstabWizardModifier);
        this.paralysisWizardModifier = strategy.changeDamage(this.paralysisWizardModifier);
        this.backstabKnightModifier = strategy.changeDamage(this.backstabKnightModifier);
        this.paralysisKnightModifier = strategy.changeDamage(this.paralysisKnightModifier);
        this.backstabPyromancerModifier = strategy.changeDamage(this.backstabPyromancerModifier);
        this.paralysisPyromancerModifier = strategy.changeDamage(this.paralysisPyromancerModifier);
    }

    @Override
    public void changeAllModifiers(float change) {
        this.backstabRogueModifier += change;
        this.paralysisRogueModifier += change;
        this.backstabWizardModifier += change;
        this.paralysisWizardModifier += change;
        this.backstabKnightModifier += change;
        this.paralysisKnightModifier += change;
        this.backstabPyromancerModifier += change;
        this.paralysisPyromancerModifier += change;
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

        if (hero instanceof Rogue) {
            this.backstabModifier = backstabRogueModifier;
            this.paralysisModifier = paralysisRogueModifier;
        }

        if (hero instanceof Pyromancer) {
            this.backstabModifier = backstabPyromancerModifier;
            this.paralysisModifier = paralysisPyromancerModifier;
        }

        if (hero instanceof Wizard) {
            this.backstabModifier = backstabWizardModifier;
            this.paralysisModifier = paralysisWizardModifier;
        }

        if (hero instanceof Knight) {
            this.backstabModifier = backstabKnightModifier;
            this.paralysisModifier = paralysisKnightModifier;
        }

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
        float backstabDamage = backstabBaseDamage + this.getLevel() * backstabBaseDamagePerLevel;

        if (this.counterAttacksRogue % attacksCoefficient == 0 && this.getLand() == 'W') {
            backstabDamage *= backstabWoodsModifier;
        }
        backstabDamage = Math.round(backstabDamage);
        this.oldCounterAttacksRogue = this.counterAttacksRogue;
        this.counterAttacksRogue++;

        /*
         Aplic amplificatorul de rasa.
        */
        backstabDamage *= backstabModifier;
        backstabDamage = Math.round(backstabDamage);
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
        float paralysisDamage = paralysisBaseDamage + paralysisBaseDamagePerLevel * this.getLevel();

        /*
         Aplic amplificatorul de rasa.
        */
        paralysisDamage *= paralysisModifier;
        paralysisDamage = Math.round(paralysisDamage);
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
