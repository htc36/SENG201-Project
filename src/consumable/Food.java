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

    public String toString() {
        String template = "[Food] %12.12s"; // name
        template += "%6d"; // price
        template += "%5d"; // heal
        template += "%5d"; // fill

        return String.format(template, super.getName(), super.getPrice(),
                super.getHealingAmount(), fillStomach);
    }
    
}
