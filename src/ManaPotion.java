public class ManaPotion implements Potion{
    private int price;
    private int weight;
    private int regenValue;

    public ManaPotion(){
        int rand = 3 + (int)(Math.random()*3);
        price = rand;
        weight = rand;
        regenValue = rand*4;
    }

    @Override
    public void usePotion(Character character) {
        character.regenMana(regenValue);
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Mana Potion";
    }
}
