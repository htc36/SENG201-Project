package outpost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

import consumable.Consumable;

public class Outpost {
    private Consumable[] consumables;
    private TreeMap<String, Consumable> conMap;
    private ArrayList<String> shoppingBag;
    private int totalPrice;

    /**
     * constructor for Outpost
     * Outpost acts like a shop for the player
     * Player can purchase foods and medical items from the Outpost
     * @param cons List of consumables that the outpost sells
     */
    public Outpost(Consumable[] cons) {
        consumables = cons;
        conMap = new TreeMap<>();
        for (Consumable c : consumables) {
            conMap.put(c.getName(), c);
        }

        shoppingBag = new ArrayList<>();
        totalPrice = 0;
    }

    /**
     * get the total price of items the player has in their shopping bag
     * @return int total price of items
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * adds an item to the player's shopping bag
     * also calculates the total price of items in the shopping bag
     * @param itemName name of the item
     */
    public void addItemToShoppingBag(String itemName) {
        shoppingBag.add(itemName);
        int itemPrice = conMap.get(itemName).getPrice();
        totalPrice += itemPrice;
    }

    /**
     * removes an item from the player's shopping bag
     * @param itemName name of the item
     */
    public void removeItemFromShoppingBag(String itemName) {
        shoppingBag.remove(itemName);
        int itemPrice = conMap.get(itemName).getPrice();
        totalPrice -= itemPrice;
    }

    /**
     * checks if the item is in player's shopping bag
     * @param itemName name of item in question
     * @return boolean true of item exists, false otherwise
     */
    public boolean hasItemInShoppingBag(String itemName) {
        return shoppingBag.contains(itemName);
    }

    /**
     * Returns the map of item name to consumable object
     * @return Map of item name to consumable object
     */
    public TreeMap<String, Consumable> getConsumableMap() {
        return conMap;
    }

    /**
     * Clears the shopping bag
     */
    public void clearShoppingBag() {
        shoppingBag.clear();
        totalPrice = 0;
    }

    /**
     * string representation of items on sale
     * @return String string of items on sale
     */
    public ArrayList<ArrayList<String>> getSaleProducts() {
        ArrayList<ArrayList<String>> template = new ArrayList<>();
        for (Consumable c : consumables) {
            template.add(c.getConsumableStats());
        }
        return template;
    }

    /**
     * checks if outpost has particular item in their stock
     * @param itemName name of the item
     * @return boolean true if in stock, false otherwise
     */
    public boolean hasItemInStock(String itemName) {
        for (String s : conMap.keySet()) {
            if (s.equals(itemName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the current state of player's shopping bag
     * @return TreeMap current state of player's shopping bag
     */
    public TreeMap<String, Integer> getShoppingBagStatus() {
        TreeMap<String, Integer> template = new TreeMap<>();
        TreeSet<String> itemsInBag = new TreeSet<String>();
        for (String s : shoppingBag) {
            itemsInBag.add(s);
        }

        for (String s : itemsInBag) {
            template.put(s, Collections.frequency(shoppingBag, s));
        }

        return template;
    }

    /**
     * Returns a random item from the consumables list
     * @return Consumable Random consumable
     */
    public Consumable getRandomItem() {
        Random rand = new Random();
        int randomItemIndex = rand.nextInt(consumables.length);
        return consumables[randomItemIndex];
    }

}
