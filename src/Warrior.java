public class Warrior extends Character{

    public Warrior(int lvl){
        super(lvl);
        maxHealth = 150;
        currentHealth = maxHealth;
        maxMana = 80;
        currentMana = maxMana;
        inventory.maxWeight = 36;
        if(level > 48)
            level = 48;
        strength = level*2 + 3;
        dexterity = level + 1;
        charisma = level/2 + 1;
        fireProtection = true;
    }

    @Override
    void levelUp() {
        level++;
        if(level > 48)
            level = 48;
        strength = level*2 + 3;
        dexterity = level + 1;
        charisma = level/2 + 1;
        System.out.println("You advanced to level " + level + "!");
    }

    public Warrior(){
        super();
        maxHealth = 150;
        currentHealth = maxHealth;
        maxMana = 80;
        currentMana = maxMana;
        inventory.maxWeight = 36;
        strength =  3;
        dexterity = 1;
        charisma = 1;
        fireProtection = true;
    }

    @Override
    public void receiveDamage(int damage) {
        int halfReceivedDamageChance = 2 * charisma + dexterity;
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
        int damage = 9 + (int)(Math.random()*3);
        int doubleDamageChance = strength;
        int random = (int) (Math.random()*101);
        if(random < doubleDamageChance){
            damage = damage *2;
        }
        return damage;
    }

}
