public class Fire extends Spell{

    @Override
    public void visit(Entity entity) {
        if(entity.fireProtection){
            System.out.println("Fire immunity!");
        }else if(entity.iceProtection){
            entity.receiveDamage(this.damage * 2);
        }else if(entity.earthProtection){
            entity.receiveDamage(this.damage / 4);
        }
    }
    @Override
    public String toString(){
        return "Fire";
    }
}
