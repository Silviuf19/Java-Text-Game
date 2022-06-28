import java.util.ArrayList;

public class Inventory {
    ArrayList<Potion> potionList = new ArrayList<>();
    int maxWeight;
    int coins;

    public void addPotion(Potion potion){
        potionList.add(potion);
        coins -= potion.getPrice();
    }
    public void removePotion(int index){
        potionList.remove(index);
    }

    public int getRemainingWeight(){
        int remaining_weight = 0;
        for (Potion potion : potionList) {
            remaining_weight += potion.getWeight();
        }
        remaining_weight = maxWeight - remaining_weight;
        return remaining_weight;
    }

}
