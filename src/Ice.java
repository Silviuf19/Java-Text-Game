public class Ice extends Spell{

    @Override
    public void visit(Entity entity) {
        if(entity.fireProtection){
            entity.receiveDamage(this.damage / 4);
        }else if(entity.iceProtection){
            System.out.println("Ice immunity!");
        }else if(entity.earthProtection){
            entity.receiveDamage(this.damage * 2);
        }
    }

    @Override
    public String toString(){
        return "Ice";
    }
}
