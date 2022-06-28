public class Rogue extends Character{
    public Rogue(int lvl){
        super(lvl);
        maxHealth = 100;
        currentHealth = maxHealth;
        maxMana = 100;
        currentMana = maxMana;
        inventory.maxWeight = 30;
        if(level > 48)
            level = 48;
        strength = level/2 + 1;
        dexterity = level*2 + 3;
        charisma = level + 1;
        earthProtection = true;
    }

    public Rogue(){
        super();
        maxHealth = 100;
        currentHealth = maxHealth;
        maxMana = 100;
        currentMana = maxMana;
        inventory.maxWeight = 30;
        strength =  1;
        dexterity = 3;
        charisma = 1;
        earthProtection = true;
    }

    @Override
    void levelUp() {
        level++;
        if(level > 48)
            level = 48;
        strength = level + 1;
        dexterity = level*2 + 3;
        charisma = level/2 + 1;
        System.out.println("You advanced to level " + level + "!");
    }

    @Override
    public void receiveDamage(int damage) {
        int halfReceivedDamageChance = 2 * charisma + strength;
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
        int damage = 12 + (int)(Math.random()*6);
        int doubleDamageChance = dexterity;
        int random = (int) (Math.random()*101);
        if(random < doubleDamageChance){
            damage = damage *2;
        }
        return damage;
    }
}
