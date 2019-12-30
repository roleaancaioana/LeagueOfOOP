package game;

public interface FighterVisitor {
    void attack(Pyromancer pyromancer);
    void attack(Knight knight);
    void attack(Rogue rogue);
    void attack(Wizard wizard);
}
