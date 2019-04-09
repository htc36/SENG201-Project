package consumable;

import java.security.InvalidParameterException;

public class Consumable implements Comparable<Consumable> {

    private int price;
    private int healingAmount;
    private String name;

    /**
     * <<auto generated javadoc comment>>
     * @param itemName <<Param Desc>>
     * @param heal <<Param Desc>>
     * @param itemPrice <<Param Desc>>
     */
    public Consumable(String itemName, int heal, int itemPrice) {
        name = itemName;
        healingAmount = heal;
        price = itemPrice;

        if (name.length() <= 0) {
            throw new InvalidParameterException("Name can't be empty");
        }

        if (price <= 0) {
            throw new InvalidParameterException("Price can't be below $0");
        }
    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    public String getName() {
        return name;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return int <<Return Desc>>
     */
    public int getPrice() {
        return price;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return int <<Return Desc>>
     */
    public int getHealingAmount() {
        return healingAmount;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param other <<Param Desc>>
     * @return boolean <<Return Desc>>
     */
    public boolean equals(Consumable other) {
        System.out.println("calloing this funcoitn");
        return other.getName() == getName();
    }

    /**
     * <<auto generated javadoc comment>>
     * @param c <<Param Desc>>
     * @return int <<Return Desc>>
     */
    @Override
    public int compareTo(Consumable c) {
        String itemName = getName();
        return itemName.compareTo(c.getName());

    }
}
