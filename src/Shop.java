import java.util.ArrayList;

public class Shop implements CellElement{
    ArrayList<Potion> potionList = new ArrayList<>();
    public Shop(){
        int rand = 2 + (int) (Math.random()*3);
        for(int i = 0; i < rand; i++){
            int rand2 = (int) (Math.random()*2);
            if(rand2 == 0){
                potionList.add(new HealthPotion());
            }else {
                potionList.add(new ManaPotion());
            }
        }
    }

    @Override
    public String toCharacter() {
        return "S";
    }

    public Potion removePotion(int index){
        Potion potion = potionList.get(index);
        System.out.println("You bought a " + potion + " potion!");
        potionList.remove(index);
        return potion;
    }

}
