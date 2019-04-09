package consumable;
public class Food extends Consumable {

    private int fillStomach;

    /**
     * <<auto generated javadoc comment>>
     * @param name <<Param Desc>>
     * @param healingAmount <<Param Desc>>
     * @param price <<Param Desc>>
     * @param fill <<Param Desc>>
     */
    public Food(String name, int healingAmount, int price, int fill) {
        super(name, healingAmount, price);
        fillStomach = fill;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return int <<Return Desc>>
     */
    public int getFillStomach() {
        return fillStomach;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
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
