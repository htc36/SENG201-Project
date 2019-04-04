package test;

import static org.junit.jupiter.api.Assertions.*;

import consumable.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ConsumableTest {

    @Test
    void medicalSupplyErrorTest() {
        assertThrows(InvalidParameterException.class, 
                () -> new MedicalSupply("", 0, 1000, false));
        assertThrows(InvalidParameterException.class, 
                () -> new MedicalSupply("Potion", 10, 0, false));
        assertThrows(InvalidParameterException.class, 
                () -> new MedicalSupply("Hi-Potion", 0, 1000, false));
    }

    @Test
    void foodErrorTest() {
        assertThrows(InvalidParameterException.class, 
                () -> new Food("Satay", 0, 1000, 1));
        assertThrows(InvalidParameterException.class, 
                () -> new Food("", 10, 100, 1));
        assertThrows(InvalidParameterException.class, 
                () -> new Food("Tacos", 10, 0, 1));
    }

    @Test
    void createFoodList() {
        ArrayList<Food> c = new ArrayList<>();
        c.add(new Food("Raw chicken", 1, 2, 3));
        assertEquals(1, c.get(0).getHealingAmount());
        assertEquals(2, c.get(0).getPrice());
        assertEquals(3, c.get(0).getFillStomach());
    }

    @Test
    void createMedicalSupplyList() {
        ArrayList<MedicalSupply> c = new ArrayList<>();
        c.add(new MedicalSupply("Elixir", 1, 2, false));
        assertEquals(1, c.get(0).getHealingAmount());
        assertEquals(2, c.get(0).getPrice());
        assertEquals(false, c.get(0).canHealSpacePlague());
    }

    @Test
    void createConsumableList() {
        ArrayList<Consumable> c = new ArrayList<>();
        c.add(new Food("Raw chicken", 10, 10, 10));
        c.add(new MedicalSupply("Elixir", 10, 10, false));
        assertEquals(2, c.size());
    }

}
