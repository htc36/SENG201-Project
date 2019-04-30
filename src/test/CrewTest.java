package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import unit.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import consumable.*;
import crew.Crew;

class CrewTest {

    private Crew c;

    @BeforeEach
    void createCrew() {
        Medic c1 = new Medic("Matthias");
        Actioneer c2 = new Actioneer("Miguel");
        ArrayList<CrewMember> members = new ArrayList<>();
        members.add(c1);
        members.add(c2);

        Spaceship s = new Spaceship("Intergalactika");

        c = new Crew(members, s);
        c.addConsumable(new Brownie());
        c.addConsumable(new Vaccine());
    }
    
    @Test
    void consumeItemTest() {
        CrewMember c1 = c.getCrewMembers().get(0);
        c1.setHunger(100);
        c1.useItem(c.popConsumable("Brownie"));
        assertEquals(true, c1.getHunger() < 100);
    }

    @Test
    void outOfActionsTest() {
        CrewMember c1 = c.getCrewMembers().get(0);
        c1.useItem(c.popConsumable("Brownie"));
        c1.useItem(c.popConsumable("Vaccine"));
        assertEquals(false, c1.stillHasActions());
        
        CrewMember c2 = c.getCrewMembers().get(1);
        c2.sleep(0);
        c2.sleep(0);

        // Actioneer has 3 actions instead of the usual 2
        assertEquals(true, c2.stillHasActions());
        c2.sleep(0);
        assertEquals(false, c2.stillHasActions());
        
        // piloting a spaceship requires 2 crew members,
        // should reduce both of their actions
        c1.setActions(2);
        c2.setActions(2);
        c1.pilotShip(c2);
        
        assertEquals(true, c1.getActions() == 1);
        assertEquals(true, c2.getActions() == 1);
    }

}
