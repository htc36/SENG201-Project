package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import consumable.Consumable;
import consumable.Food;
import consumable.MedicalSupply;
import outpost.Outpost;

class OutpostTest {

    private Outpost o;

    @BeforeEach
    public void setUp() {
        Food f1 = new Food("Banana", 100, 180, 1);
        Food f2 = new Food("Melon", 100, 180, 1);
        Food f3 = new Food("Apple", 100, 180, 1);
        Food f4 = new Food("Beef", 100, 180, 1);
        Food f5 = new Food("Pork", 100, 180, 1);
        Food f6 = new Food("Chicken", 100, 180, 1);

        MedicalSupply m1 = new MedicalSupply("M1", 10, 10, false);
        MedicalSupply m2 = new MedicalSupply("Vaxxin", 10, 10, false);
        MedicalSupply m3 = new MedicalSupply("Cockroach Powder", 10, 10, false);

        Consumable[] c = new Consumable[]{f1, f2, f3, f4, f5, f6, m1, m2, m3};
        o = new Outpost(c);
    }

    @Test
    public void createOutpostTest() {
        // now lets try putting stuff to the shopping bag
        o.addItemToShoppingBag("Banana");
        o.addItemToShoppingBag("M1");
        o.addItemToShoppingBag("Vaxxin");
        assertEquals(200, o.getTotalPrice());

        o.removeItemFromShoppingBag("Banana");
        assertEquals(20, o.getTotalPrice());
    }

}
