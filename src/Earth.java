public class Earth extends Spell{

    @Override
    public void visit(Entity entity) {
        if(entity.fireProtection){
            entity.receiveDamage(this.damage * 2);
        }else if(entity.iceProtection){
            entity.receiveDamage(this.damage / 4);
        }else if(entity.earthProtection){
            System.out.println("Earth immunity!");
        }
    }

    @Override
    public String toString(){
        return "Earth";
    }
}
