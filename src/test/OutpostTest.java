package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import consumable.*;
import outpost.Outpost;

class OutpostTest {

    private Outpost o;
    private int totalPrice;

    @BeforeEach
    void setUp() {
        Food f1 = new Brownie();
        Food f2 = new Dumplings();
        Food f3 = new FriedRice();
        Food f4 = new Hotbot();
        Food f5 = new TikkaMasala();
        Food f6 = new SpaceCake();

        MedicalSupply m1 = new Vaccine();
        MedicalSupply m2 = new PolyJuice();
        MedicalSupply m3 = new PickledPlum();
        
        totalPrice = f1.getPrice() + f2.getPrice() + f3.getPrice() + f4.getPrice();
        totalPrice += f5.getPrice() + f6.getPrice() + m1.getPrice() + m2.getPrice() + m3.getPrice();

        Consumable[] c = new Consumable[]{f1, f2, f3, f4, f5, f6, m1, m2, m3};
        o = new Outpost(c);
    }

    @Test
    void shoppingBagPriceTest() {
        // now lets try putting stuff to the shopping bag
        for (String item : o.getConsumableMap().keySet()) {
            o.addItemToShoppingBag(item);
        }
        
        assertEquals(totalPrice, o.getTotalPrice());
    }
    
    @Test
    void clearShoppingBagTest() {
        // now lets try putting stuff to the shopping bag
        for (String item : o.getConsumableMap().keySet()) {
            o.addItemToShoppingBag(item);
        }
        
        assertEquals(true, o.hasItemInShoppingBag("Vaccine"));
        assertEquals(true, o.hasItemInShoppingBag("PolyJuice"));
        
        o.removeItemFromShoppingBag("Vaccine");
        assertEquals(false, o.hasItemInShoppingBag("Vaccine"));

        o.clearShoppingBag();

        
        assertEquals(0, o.getTotalPrice());
    }
    
    @Test
    void hasItemInStockTest() {
        String[] itemNames = new String[] {"Brownie", "Dumplings", "FriedRice", "Hotbot",
                "TikkaMasala", "SpaceCake", "Vaccine", "PolyJuice", "PickledPlum"};
        
        for (int i = 0; i < itemNames.length; i++) {
            assertEquals(true, o.hasItemInStock(itemNames[i]));
        }
        
        assertEquals(false, o.hasItemInStock("Pie"));
        
    }
    
    @Test
    void shoppingBagStatusTest() {
        // now lets try putting stuff to the shopping bag
        for (String item : o.getConsumableMap().keySet()) {
            o.addItemToShoppingBag(item);
        }
        
        String[] itemNames = new String[] {"Brownie", "Dumplings", "FriedRice", "Hotbot",
                "PickledPlum", "PolyJuice", "SpaceCake", "TikkaMasala", "Vaccine"};
        
        int i = 0;
        TreeMap<String, Integer> shoppingBag = o.getShoppingBagStatus();
        for (Map.Entry<String, Integer> entry : shoppingBag.entrySet()) {
            assertEquals(itemNames[i], entry.getKey());
            assertEquals(1, (int) entry.getValue());
            i++;
        }
        
    }
    
    @Test
    void getRandomItemTest() {
        Consumable c = o.getRandomItem();
        String[] itemNames = new String[] {"Brownie", "Dumplings", "FriedRice", "Hotbot",
                "TikkaMasala", "SpaceCake", "Vaccine", "PolyJuice", "PickledPlum"};
        
        boolean found = false;
        for (int i = 0; i < itemNames.length; i++) {
            if (itemNames[i].equals(c.getName())) 
                found = true;
            
        }
        
        assertEquals(true, found);
    }
    
    @Test
    void getSaleItemsTest() {
        String[] itemNames = new String[] {"Brownie", "Dumplings", "FriedRice", "Hotbot",
                "TikkaMasala", "SpaceCake", "Vaccine", "PolyJuice", "PickledPlum"};
        
        int i = 0;
        ArrayList<ArrayList<String>> saleItems = o.getSaleProducts();
        for (ArrayList<String> saleItem : saleItems) {
            assertEquals(itemNames[i], saleItem.get(0));
            i++;
        }
        
    }

}
