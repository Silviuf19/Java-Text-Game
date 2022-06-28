public class HealthPotion implements Potion{
    private int price;
    private int weight;
    private int regenValue;

    public HealthPotion(){
        int rand = 3 + (int)(Math.random()*3);
        price = rand+1;
        weight = rand;
        regenValue = rand*4;
    }

    @Override
    public void usePotion(Character character) {
        character.regenHealth(regenValue);
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
        return "Health Potion";
    }
}
