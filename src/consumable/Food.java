package consumable;
public class Food extends Consumable {

    private int fillStomach;

    public Food(String name, int healingAmount, int price, int fill) {
        super(name, healingAmount, price);
        fillStomach = fill;
    }

    public int getFillStomach() {
        return fillStomach;
    }
    
}
