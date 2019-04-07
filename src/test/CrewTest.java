package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import unit.*;

import org.junit.jupiter.api.Test;

import consumable.Consumable;
import consumable.Food;
import consumable.MedicalSupply;
import crew.Crew;

class CrewTest {

    @Test
    void createCrew() {
        CrewMember c1 = new CrewMember("Walter", 10);
        CrewMember c2 = new CrewMember("Richard", 10);
        CrewMember c3 = new CrewMember("Matthias", 10);
        ArrayList<CrewMember> members = new ArrayList<>();
        members.add(c1);
        members.add(c2);
        members.add(c3);

        Spaceship s = new Spaceship("Intergalactika");

        Crew c = new Crew(members, s);
        c.addConsumable(new Food("Rice", 10, 10, 10));
        c.addConsumable(new MedicalSupply("Snake's Egg", 10, 10, false));

        Consumable item = c.popConsumable("Rice");
        String itemName = item.getName();
        assertEquals("Rice", itemName);

        c.popRandomItem();

        c.addConsumable(new Food("Blue cake", 10, 10, 10));
        c.addConsumable(new Food("Red cake", 10, 10, 10));
        c.addConsumable(new Food("Blue cake", 10, 10, 10));
        c.addConsumable(new Food("Blue cake", 10, 10, 10));

        assertEquals(3, c.getConsumableCount("Blue cake"));
        assertEquals(1, c.getConsumableCount("Red cake"));
    }

}