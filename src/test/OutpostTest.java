package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import consumable.*;
import outpost.Outpost;

class OutpostTest {

    private Outpost o;
    private int totalPrice;

    @BeforeEach
    public void setUp() {
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
    public void shoppingBagPriceTest() {
        // now lets try putting stuff to the shopping bag
        for (String item : o.getConsumableMap().keySet()) {
            o.addItemToShoppingBag(item);
        }
        
        assertEquals(totalPrice, o.getTotalPrice());
    }
    
    @Test
    public void clearShoppingBagTest() {
        // now lets try putting stuff to the shopping bag
        for (String item : o.getConsumableMap().keySet()) {
            o.addItemToShoppingBag(item);
        }
        
        o.clearShoppingBag();
        
        assertEquals(0, o.getTotalPrice());
        
    }

}
