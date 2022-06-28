

public abstract class Spell implements Visitor{
    public int damage = 20 + (int) (Math.random()*11);
    public int mana = 30 + (int) (Math.random()*11);

    public abstract void visit(Entity entity);

}
