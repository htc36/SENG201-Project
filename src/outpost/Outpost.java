package outpost;

import java.util.ArrayList;
import java.util.TreeMap;

import consumable.Consumable;
import crew.Crew;

public class Outpost {
    private Consumable[] consumables;
    private TreeMap<String, Consumable> conMap;
    private ArrayList<String> shoppingBag;
    private int totalPrice;

    public Outpost(Consumable[] cons) {
        consumables = cons;
        conMap = new TreeMap<>();
        for (Consumable c : consumables) {
            conMap.put(c.getName(), c);
        }

        shoppingBag = new ArrayList<>();
        totalPrice = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
    public int getPrice(String itemName) {
    	return conMap.get(itemName).getPrice();
    }

    public void addItemToShoppingBag(String itemName) {
        shoppingBag.add(itemName);
        int itemPrice = conMap.get(itemName).getPrice();
        totalPrice += itemPrice;
    }

    public void removeItemFromShoppingBag(String itemName) {
        shoppingBag.remove(itemName);
        int itemPrice = conMap.get(itemName).getPrice();
        totalPrice -= itemPrice;
    }

    public void purchaseItems(Crew c) {
        c.shellOutMoney(totalPrice);
        for (String itemName : shoppingBag) {
            Consumable purchasedItem = conMap.get(itemName);
            c.addConsumable(purchasedItem);
        }

        totalPrice = 0;
        shoppingBag.clear();
    }

    public String saleProductsToString() {
        String template = "";
        for (Consumable c : consumables) {
            template += c + "\n";
        }
        return template;
    }

    public boolean hasItemInStock(String itemName) {
        for (String s : conMap.keySet()) {
            if (s.equals(itemName)) {
                return true;
            }
        }

        return false;
    }

    public String shoppingBagToString() {
        String template = "";
        for (String s : shoppingBag) {
            template += s + "\n";
        }

        return template;
    }

}
