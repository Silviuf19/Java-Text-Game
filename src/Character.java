public abstract class Character extends Entity {
    public String name;
    public int current_x;
    public int current_y;
    Inventory inventory = new Inventory();
    public int xp;
    public int level;
    public int strength;
    public int charisma;
    public int dexterity;

    public Character(){
        level = 0;
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
    }

    public Character(int lvl){
        if(lvl > 0)
            level = lvl;

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
    }

    abstract void levelUp();

    public void addXP(int exp){
        xp += exp;
        System.out.println("You earned " + exp + " XP!");
        if(xp >= (40 + 15*(level-1))){
            xp -= (40 + 15*(level-1));
            levelUp();
        }
    }

    public void printPotionList(){
        for(int i = 1; i <= inventory.potionList.size(); i++){
            System.out.println(i  +  ". " + inventory.potionList.get(i-1));
        }
        System.out.println("0. Exit");
    }

    public void printSpellList(){
        for(int i = 1; i <= spells.size(); i++){
            System.out.println(i + ". " + spells.get(i-1));
        }
    }

    public int useSpellOnEnemy(Enemy enemy){
        printSpellList();
        Helper helper = new Helper();
        boolean ok = false;
        while(!ok){
            try {
                int index = Integer.parseInt(helper.getUserInput("Choose spell number: "));
                if(index > 0 && index <= spells.size()){
                    if(spells.get(index-1).mana <= currentMana) {
                        this.useSpell(spells.get(index-1),enemy);
                        ok = true;
                    }else{
                        System.out.println("Not enough mana!");
                        return 0;
                    }
                }else{
                    System.out.println("Invalid command!");
                }
            }catch (Exception ex){
                System.out.println("Invalid command!");
            }
        }
        return 1;
    }

    public void usePotion(){
        printPotionList();
        Helper helper = new Helper();
        boolean ok = false;
        while(!ok){
            try {
                int index = Integer.parseInt(helper.getUserInput("Choose potion number: "));
                if(index > 0 && index <= inventory.potionList.size()){
                    inventory.potionList.get(index-1).usePotion(this);
                    inventory.removePotion(index-1);
                    ok = true;
                }else{
                    System.out.println("Invalid command!");
                }
            }catch (Exception ex){
                System.out.println("Invalid command!");
            }
        }
    }

    public boolean buyPotion(Potion potion){
        if(inventory.getRemainingWeight() >= potion.getWeight() && inventory.coins >= potion.getPrice()) {
            inventory.addPotion(potion);
            return true;
        }else{
            if(inventory.coins < potion.getPrice()){
                System.out.println("You don't have enough coins!");
            }
            if(inventory.getRemainingWeight() < potion.getWeight()){
                System.out.println("Your inventory is too tight for this potion!");
            }
            return false;
        }
    }

    public void getCoinsCell(){
        int rand1 = (int)(Math.random()*100);
        if(rand1 < 20){
            int rand2 = 2 + (int)(Math.random()*2);
            inventory.coins = inventory.coins + rand2;
            System.out.println("Lucky! You found " + rand2  +" coins!");
        }
    }

    public void getCoinsEnemy(){
        int rand1 = (int)(Math.random()*100);
        if(rand1 < 80){
            int rand2 = 5 + (int)(Math.random()*2);
            inventory.coins += rand2;
            System.out.println("Lucky! Enemy dropped " + rand2  +" coins!");
        }
    }
}



