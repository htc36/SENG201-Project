package consumable;
public class Food extends Consumable {

    private int fillStomach;

    /**
     * Constructor for Food object
     * @param name name of the food
     * @param healingAmount health it increases when consumed
     * @param price price of the item
     * @param fill hunger level it decreases when consumed
     */
    public Food(String name, int healingAmount, int price, int fill) {
        super(name, healingAmount, price);
        fillStomach = fill;
    }

    /**
     * @return int hunger level it decreases when consumed
     */
    public int getFillStomach() {
        return fillStomach;
    }

    /**
     * string representation of a Food 
     * it is formatted such that it fits nicely in a table
     * @return String string representation of a Food
     */
    public String toString() {
        String template = "[Food] %12.12s"; // name
        template += "%6d"; // price
        template += "%5d"; // heal
        template += "%5d"; // fill

        return String.format(template, super.getName(), super.getPrice(),
                super.getHealingAmount(), fillStomach);
    }

}
