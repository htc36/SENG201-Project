package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import unit.*;

import org.junit.jupiter.api.Test;

class UnitTest {

    @Test
    void createMedic() {
        String name = "medic001";
        Medic u = new Medic(name, 10);
        u.reduceHealth(10); // should only take 5 damage
        assertEquals(95, u.getHealth());
        u.reduceHealth(20); // should only take 10 damage
        assertEquals(85, u.getHealth());
        u.reduceHealth(1000); // should set health to 0
        assertEquals(0, u.getHealth());
    }

    @Test
    void medicPartOfCrewMemberTest() {
        String name = "medic002";
        Medic u = new Medic(name, 10);
        ArrayList<CrewMember> cr = new ArrayList<>();
        cr.add(u);
        assertEquals(1, cr.size());
    }

    @Test
    void createUnit() {
        String name = "automaton007";
        Unit u = new Unit(name);
        assertEquals(100, u.getHealth());
        assertEquals(name, u.getName());
        assertEquals(true, u.isAlive());
    }

    @Test
    void createCrewMember() {
        String name = "johncena";
        CrewMember c = new CrewMember(name, 50);
        Spaceship s = new Spaceship(name);
        s.reduceHealth(60); // ship should have 40 hp left
        c.reduceAction();
        assertEquals(1, c.getActions());
        c.repairShield(s); // adds 10 to ship hp
        assertEquals(50, s.getHealth());
        assertEquals(0, c.getActions());
    }

    @Test
    void createSpaceship() {
        String name = "deathstar";
        Spaceship s = new Spaceship(name);
        assertEquals(100, s.getHealth());
        assertEquals(name, s.getName());
        assertEquals(true, s.isAlive());
        s.repairShield(100);
        assertEquals(100, s.getHealth());
    }

}
