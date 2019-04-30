package consumable;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Consumable implements Comparable<Consumable> {

    private int price;
    private int healingAmount;
    private String name;

    /**
     * Constructor for a Consumable
     * @param itemName the name of the item
     * @param heal healing amount
     * @param itemPrice price of the item
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
     * return the name of the consumable
     * @return String name of consumable
     */
    public String getName() {
        return name;
    }

    /**
     * return the price of the consumable
     * @return int price of consumable
     */
    public int getPrice() {
        return price;
    }

    /**
     * get the healing the consumable provides when consumed
     * @return int healing amount when consumed
     */
    public int getHealingAmount() {
        return healingAmount;
    }

    /**
     * checks if two consumables are similar
     * @param other the other consumable object 
     * @return boolean true if similar, false otherwise
     */
    public boolean equals(Consumable other) {
        return other.getName() == getName();
    }

    /**
     * compares two consumable, useful when ordering
     * @param c the other consumable
     * @return int ordering
     */
    @Override
    public int compareTo(Consumable c) {
        String itemName = getName();
        return itemName.compareTo(c.getName());
    }

    /**
     * Returns the stats of a consumable as ArrayList of String
     * @return ArrayList<String> Status of consumable
     */
    public ArrayList<String> getConsumableStats() {
        ArrayList<String> template = new ArrayList<>();
        template.add(String.format("%s", name)); // name
        template.add(String.format("%d", price)); // price
        template.add(String.format("%d", healingAmount)); // heal
        return template;
    }
}
