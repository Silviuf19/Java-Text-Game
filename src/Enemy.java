
public class Enemy extends Entity implements CellElement {

    public Enemy(){
        maxHealth = 30 + (int)(Math.random()*31);
        currentHealth = maxHealth;
        maxMana = 60 + (int)(Math.random()*31);
        currentMana = maxMana;

        int rand = 2 + (int)(Math.random()*3);
        for(int i = 0; i <= rand; i++){
            int rand2 = (int)(Math.random()*3);
            if(rand2 == 0){
                spells.add(new Earth());
            }else if(rand2 == 1){
                spells.add(new Ice());
            }else if(rand2 == 2){
                spells.add(new Fire());
            }
        }

        rand = (int)(Math.random()*3);
        if(rand == 0){
            iceProtection = true;
        }else if(rand == 1){
            fireProtection = true;
        }else if(rand == 2){
            fireProtection = true;
        }
    }

    @Override
    public String toCharacter() {
        return "E";
    }


    @Override
    public void receiveDamage(int damage) {
        int random = (int) (Math.random()*101);
        if(random < 50){
            currentHealth -= damage/2;
            System.out.println("Enemy got " + damage/2 + " damage");
        }else{
            currentHealth -= damage;
            System.out.println("Enemy got " + damage + " damage");
        }
        if(currentHealth <= 0){
            currentHealth = 0;
            System.out.println("Enemy defeated!");
        }
    }

    @Override
    public int getDamage() {
        int damage = 5 + (int)(Math.random()*8);
        int random = (int) (Math.random()*100);
        if(random < 50){
            damage = damage *2;
        }
        return damage;
    }

    public void attackCharacter(Entity entity){
        int random = (int) (Math.random()*100);
        int chance = 20 + (int)(Math.random()*20);
        if(random < chance){
            if(currentMana >= spells.get(0).mana){
                System.out.println("Enemy used " + spells.get(0) + "spell!");
                this.useSpell(spells.get(0), entity);
            } else{
                System.out.println("Enemy attacked");
                entity.receiveDamage(this.getDamage());
            }
        }else{
            entity.receiveDamage(this.getDamage());
            System.out.println("Enemy attacked");
        }
    }
}
