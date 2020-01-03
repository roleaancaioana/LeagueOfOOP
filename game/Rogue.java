package game;

import angels.AngelVisitor;
import strategies.Strategy;

/**
 * Aceasta clasa ilustreaza comportamentul unui erou de tip Rogue.
 */
public class Rogue extends Hero {
    private final int hpRogue = 600;
    private final int hpPerLevelRogue = 40;
    private int counterAttacksRogue;
    private final float backstabBaseDamage = 200;
    private final float backstabBaseDamagePerLevel = 20;
    private final float backstabWoodsModifier = 1.5f;
    private final float paralysisBaseDamage = 40;
    private final float paralysisBaseDamagePerLevel = 10;
    private final float bonusWoodsModifier = 1.15f;
    private final int attacksCoefficient = 3;
    private final float initialBackstabKnightModifier = 0.9f;
    private final float initialParalysisKnightModifier = 0.8f;
    private final float initialBackstabPyromancerModifier = 1.25f;
    private final float initialParalysisPyromancerModifier = 1.2f;
    private final float initialBackstabRogueModifier = 1.2f;
    private final float initialParalysisRogueModifier = 0.9f;
    private final float initialBackstabWizardModifier = 1.25f;
    private final float initialParalysisWizardModifier = 1.25f;
    private int oldCounterAttacksRogue;
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
        this.backstabRogueModifier = initialBackstabRogueModifier;
        this.paralysisRogueModifier = initialParalysisRogueModifier;
        this.backstabWizardModifier = initialBackstabWizardModifier;
        this.paralysisWizardModifier = initialParalysisWizardModifier;
        this.backstabKnightModifier = initialBackstabKnightModifier;
        this.paralysisKnightModifier = initialParalysisKnightModifier;
        this.backstabPyromancerModifier = initialBackstabPyromancerModifier;
        this.paralysisPyromancerModifier = initialParalysisPyromancerModifier;
    }

    @Override
    public final void executeStrategy() {
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
    public final void changeAllModifiers(final float change) {
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

        int intBackstabDamage = getBackstabDamage();

        int intParalysisDamage = getParalysisDamage();
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
     * @return damage-ul rezultat in urma aplicarii abilitatii Backstab
     */
    private int getBackstabDamage() {
        float backstabDamage = backstabBaseDamage + this.getLevel() * backstabBaseDamagePerLevel;

        if (this.counterAttacksRogue % attacksCoefficient == 0 && this.getLand() == 'W') {
            backstabDamage *= backstabWoodsModifier;
        }
        backstabDamage = Math.round(backstabDamage);
        this.oldCounterAttacksRogue = this.counterAttacksRogue;
        this.counterAttacksRogue++;

        backstabDamage *= backstabModifier;
        backstabDamage = Math.round(backstabDamage);

        if (this.getLand() == 'W') {
            backstabDamage *= bonusWoodsModifier;
        }
        return (Math.round(backstabDamage));
    }

    /**
     * Metoda ma va ajuta la calculul damage-ului rezultat in urma
     * aplicarii abilitatii Paralysis a acestui erou.
     * @return damage-ul rezultat in urma aplicarii abilitatii Paralysis
     */
    private int getParalysisDamage() {
        float paralysisDamage = paralysisBaseDamage + paralysisBaseDamagePerLevel * this.getLevel();

        paralysisDamage *= paralysisModifier;
        paralysisDamage = Math.round(paralysisDamage);

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
    public final void receiveAngelPower(final AngelVisitor angelVisitor) {
        angelVisitor.angelPower(this);
    }
}
