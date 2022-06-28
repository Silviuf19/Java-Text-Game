import java.util.ArrayList;

public abstract class Entity implements Element{
    ArrayList<Spell> spells = new ArrayList<>();
    public int currentHealth;
    public int maxHealth;
    public int currentMana;
    public int maxMana;
    boolean fireProtection;
    boolean iceProtection;
    boolean earthProtection;

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void useSpell(Spell spell, Entity entity){
        entity.accept(spell);
        currentMana -= spell.mana;
        spells.remove(spell);
    }

    public void regenHealth(int healthAmount){
        currentHealth += healthAmount;
        if(currentHealth > maxHealth){
            currentHealth = maxHealth;
        }
    }

    public void regenMana(int manaAmount){
        currentMana += manaAmount;
        if(currentMana > maxMana){
            currentMana = maxMana;
        }
    }

    public abstract void receiveDamage(int damage);
    public abstract int getDamage();

}
