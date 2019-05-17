package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import unit.*;

import org.junit.jupiter.api.Test;

class UnitTest {

    @Test
    void createMedic() {
        String name = "medic001";
        Medic u = new Medic(name);
        u.reduceHealth(10); // should only take 5 damage
        assertEquals(95, u.getHealth());
        u.reduceHealth(20); // should only take 10 damage
        assertEquals(85, u.getHealth());
        u.reduceHealth(1000); // should set health to 0
        assertEquals(0, u.getHealth());
        u.setFatique(10);
        assertEquals(10, u.getFatique());
        assertEquals(20, u.getLuck());
        u.increaseFatique(10000);
        assertEquals(100, u.getFatique());
        assertEquals(0, u.getHunger());
        assertEquals(true, u.stillHasActions());
        u.setHunger(10);
        assertEquals(10, u.getHunger());
        u.setActions(10);
        assertEquals(10, u.getActions());
    }

    @Test
    void medicPartOfCrewMemberTest() {
        String name = "medic002";
        Medic u = new Medic(name);
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
    void createSpaceship() {
        String name = "deathstar";
        Spaceship s = new Spaceship(name);
        assertEquals(100, s.getHealth());
        assertEquals(name, s.getName());
        assertEquals(true, s.isAlive());
        s.repairShield(100);
        assertEquals(100, s.getHealth());
        ArrayList<String> spaceshipStatus = s.getShipStatus();
        assertEquals("deathstar", spaceshipStatus.get(0));
        assertEquals("100", spaceshipStatus.get(1));
    }
    
    @Test
    void builderTest() {
        Spaceship s = new Spaceship("Deathstar");
        s.setHealth(10);
        Builder b = new Builder("Bob");
        b.repairShield(s, 10);
        assertEquals(30, s.getHealth());
        
        ArrayList<String> bString = b.getCrewString();
        assertEquals("Bob", bString.get(0));
    }
    
    @Test
    void hungusTest() {
        Hungus h = new Hungus("Bob");
        h.increaseHunger(50);
        ArrayList<String> hString = h.getCrewString();
        assertEquals("25", hString.get(4));
        h.decreaseHunger(13);
        hString = h.getCrewString();
        assertEquals("0", hString.get(4));
    }
    
    @Test
    void sleeperTest() {
        Sleeper s = new Sleeper("Bob");
        s.increaseFatique(50);
        s.sleep(25);
        ArrayList<String> sString = s.getCrewString();
        assertEquals("0", sString.get(5));
    }
    
    @Test
    void actioneerTest() {
        Actioneer a = new Actioneer("Bob");
        a.searchPlanet();
        a.searchPlanet();
        a.searchPlanet();
        assertEquals(0, a.getActions());
        a.refreshActions(0);
        assertEquals(3, a.getActions());
    }

}
