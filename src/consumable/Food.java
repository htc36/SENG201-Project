package consumable;

import java.util.ArrayList;

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
     * Returns the stats of a consumable as ArrayList of String
     * @return ArrayList<String> Status of consumable
     */
    public ArrayList<String> getConsumableStats() {
        ArrayList<String> template = super.getConsumableStats();
        template.add("[Food]");
        template.add(String.format("%s", fillStomach));
        return template;
    }

}
