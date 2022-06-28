public class Mage extends Character{

    public Mage(int lvl){
        super(lvl);
        maxMana = 150;
        currentMana = maxMana;
        maxHealth = 80;
        currentHealth = maxHealth;
        if(level > 48)
            level = 48;
        strength = level/2 + 1;
        dexterity = level + 1;
        charisma = level*2 + 3;
        inventory.maxWeight = 24;
        iceProtection = true;
    }

    public Mage(){
        super();
        maxMana = 150;
        currentMana = maxMana;
        inventory.maxWeight = 24;
        maxHealth = 80;
        currentHealth = maxHealth;
        strength =  1;
        dexterity = 1;
        charisma = 3;
        iceProtection = true;
    }

    @Override
    void levelUp() {
        level++;
        if(level > 48)
            level = 48;
        strength = level/2 + 1;
        dexterity = level + 1;
        charisma = level*2 + 3;
        System.out.println("You advanced to level " + level + "!");
    }

    @Override
    public void receiveDamage(int damage) {
        int halfReceivedDamageChance = 2 * strength + dexterity;
        int random = (int) (Math.random()*101);
        if(random < halfReceivedDamageChance){
            currentHealth -= damage/2;
            if(damage/2 == 0){
                System.out.println("Missed!");
            }
        }else{
            currentHealth -= damage;
            if(damage == 0){
                System.out.println("Missed!");
            }
        }
        if(currentHealth <= 0){
            currentHealth = 0;
        }
    }

    @Override
    public int getDamage() {
        int damage = 15 + (int)(Math.random()*8);
        int doubleDamageChance = charisma;
        int random = (int) (Math.random()*101);
        if(random < doubleDamageChance){
            damage = damage *2;
        }
        return damage;
    }
}
