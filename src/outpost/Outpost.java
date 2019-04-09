package outpost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.TreeSet;

import consumable.Consumable;
import crew.Crew;

public class Outpost {
    private Consumable[] consumables;
    private TreeMap<String, Consumable> conMap;
    private ArrayList<String> shoppingBag;
    private int totalPrice;

    /**
     * <<auto generated javadoc comment>>
     * @param cons <<Param Desc>>
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
     * <<auto generated javadoc comment>>
     * @return int <<Return Desc>>
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param itemName <<Param Desc>>
     */
    public void addItemToShoppingBag(String itemName) {
        shoppingBag.add(itemName);
        int itemPrice = conMap.get(itemName).getPrice();
        totalPrice += itemPrice;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param itemName <<Param Desc>>
     */
    public void removeItemFromShoppingBag(String itemName) {
        shoppingBag.remove(itemName);
        int itemPrice = conMap.get(itemName).getPrice();
        totalPrice -= itemPrice;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param itemName <<Param Desc>>
     * @return boolean <<Return Desc>>
     */
    public boolean hasItemInShoppingBag(String itemName) {
        return shoppingBag.contains(itemName);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param c <<Param Desc>>
     */
    public void purchaseItems(Crew c) {
        c.shellOutMoney(totalPrice);
        for (String itemName : shoppingBag) {
            Consumable purchasedItem = conMap.get(itemName);
            c.addConsumable(purchasedItem);
        }

        totalPrice = 0;
        shoppingBag.clear();
    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    public String saleProductsToString() {
        String template = "";
        for (Consumable c : consumables) {
            template += c + "\n";
        }
        return template;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param itemName <<Param Desc>>
     * @return boolean <<Return Desc>>
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
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    public String shoppingBagToString() {
        String template = "";
        TreeSet<String> itemsInBag = new TreeSet<String>();
        for (String s : shoppingBag) {
            itemsInBag.add(s);
        }

        for (String s : itemsInBag) {
            template += Collections.frequency(shoppingBag, s) + "x" + s + "\n";
        }

        return template;
    }

}
