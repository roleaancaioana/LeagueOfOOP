package game;

public interface Visitor {
    void attack(Pyromancer pyromancer);
    void attack(Knight knight);
    void attack(Rogue rogue);
    void attack(Wizard wizard);
}
