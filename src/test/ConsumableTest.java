package test;

import static org.junit.jupiter.api.Assertions.*;

import consumable.*;
import unit.CrewMember;
import unit.Explorer;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsumableTest {
    
    private CrewMember c1;
    
    /**
     * Create a random Crew Member for our test case
     * Hang in there, Dora!
     */
    @BeforeEach
    public void setup() {
        c1 = new Explorer("Dora");
    }
    
    /**
     * Vaccine and PolyJuice should cure space plague
     * but not Pickled Plum
     */
    @Test
    void medicalCurePlagueTest() {
        c1.makeSick();
        c1.useItem(new Vaccine());
        assertEquals(false, c1.isSick());
        c1.makeSick();
        c1.useItem(new PolyJuice());
        assertEquals(false, c1.isSick());
        c1.makeSick();
        c1.useItem(new PickledPlum());
        assertEquals(true, c1.isSick());
    }
    
    /**
     * Every food should decrease hunger by some amount
     */
    @Test
    void cureHungerTest() {
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Brownie());
        foods.add(new Dumplings());
        foods.add(new FriedRice());
        foods.add(new Hotbot());
        foods.add(new SpaceCake());
        foods.add(new TikkaMasala());
        for (Food item : foods) {
            c1.setHunger(100);
            c1.useItem(item);
            assertEquals(true, c1.getHunger() < 100);
        }
    }
    
    /**
     * We should be able to create an array list of 
     * consumables containing foods and medical supplies
     * thank god (or God) for inheritance
     */
    @Test
    void inheritanceTest() {
        ArrayList<Consumable> consumablesList = new ArrayList<>();
        consumablesList.add(new Brownie());
        consumablesList.add(new Vaccine());
    }

}
