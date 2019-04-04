package test;

import static org.junit.jupiter.api.Assertions.*;

import unit.Unit;
import unit.Spaceship;
import unit.CrewMember;

import org.junit.jupiter.api.Test;

class UnitTest {

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
        assertEquals(1, c.getActionsLeft());
        c.repairShield(s); // adds 10 to ship hp
        assertEquals(50, s.getHealth());
        assertEquals(0, c.getActionsLeft());
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
