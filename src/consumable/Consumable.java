package consumable;

import java.security.InvalidParameterException;
import java.util.ArrayList;


/**
 * Defines Abstract class for all the consumables in the game
 */
public abstract class Consumable implements Comparable<Consumable> {

    /**
     * The price of the consumable
     */
    private int price;
    /**
     * The healing amount of the consumable
     */
    private int healingAmount;
    /**
     * The name of the consumable
     */
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
     * Return the name of the consumable
     * @return String name of consumable
     */
    public String getName() {
        return name;
    }

    /**
     * Return the price of the consumable
     * @return int price of consumable
     */
    public int getPrice() {
        return price;
    }

    /**
     * Get the healing the consumable provides when consumed
     * @return int healing amount when consumed
     */
    public int getHealingAmount() {
        return healingAmount;
    }

    /**
     * Checks if two consumables are similar
     * @param other the other consumable object 
     * @return boolean true if similar, false otherwise
     */
    public boolean equals(Consumable other) {
        return other.getName().equals(getName());
    }

    /**
     * Compares two consumable, useful when ordering
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
